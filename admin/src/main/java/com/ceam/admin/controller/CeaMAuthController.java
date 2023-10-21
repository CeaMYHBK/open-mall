package com.ceam.admin.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ceam.admin.dto.AuthUser;
import com.ceam.admin.dto.CeaMSysUserDTO;
import com.ceam.admin.entity.CeamSysUser;
import com.ceam.admin.service.ICeamSysUserService;
import com.ceam.admin.vo.JwtUser;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.exception.ServiceException;
import com.ceam.common.utils.R;
import com.ceam.common.utils.RedisUtils;
import com.ceam.common.utils.SecurityUtils;
import com.ceam.common.utils.StringUtils;
import com.ceam.token.jwt.JwtTokenUtil;
import com.ceam.token.jwt.SecurityProperties;
import com.ceam.token.service.OnlineUserService;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author CeaM
 * 2023/01/20 16:37
 **/
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class CeaMAuthController {

    @Value("${loginCode.expiration}")
    private Long expiration;
    @Value("${rsa.private_key}")
    private String privateKey;
    @Value("${single.login}")
    private Boolean singleLogin;
    private final RedisUtils redisUtils;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenUtil tokenUtil;
    private final SecurityProperties properties;
    private final OnlineUserService onlineUserService;
    @Autowired
    private ICeamSysUserService ceamSysUserService;

    public CeaMAuthController(SecurityProperties properties,
                              RedisUtils redisUtils,
                              JwtTokenUtil tokenUtil,
                              UserDetailsService userDetailsService,
                              AuthenticationManagerBuilder authenticationManagerBuilder,
                              OnlineUserService onlineUserService) {
        this.properties = properties;
        this.redisUtils = redisUtils;
        this.tokenUtil = tokenUtil;
        this.userDetailsService = userDetailsService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.onlineUserService = onlineUserService;
    }

    @PostMapping("/attention")
    public R<Object> attention() {
        LambdaUpdateWrapper<CeamSysUser> updateWrapper = Wrappers.<CeamSysUser>lambdaUpdate()
                .set(CeamSysUser::getAttention, GlobalConstants.TRUE)
                .eq(CeamSysUser::getUsername, "admin");
        ceamSysUserService.update(updateWrapper);
        return R.ok();
    }

    @PostMapping(value = "/login")
    public R<Object> login(@Validated @RequestBody AuthUser authUser,
                           HttpServletRequest request) {
        String username = authUser.getUsername();
        String password = authUser.getPassword();
        // 密码解密
        RSA rsa = new RSA(privateKey, null);
        String password1 = new String(rsa.decrypt(password, KeyType.PrivateKey));

        // 查询验证码
        String code = (String) redisUtils.get(authUser.getUuid());
        // 清除验证码
        redisUtils.del(authUser.getUuid());
        if (StringUtils.isBlank(code)) {
            throw new ServiceException("验证码不存在或已过期");
        }
        if (StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
            throw new ServiceException("验证码错误");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password1);

        Authentication authentication = authenticationManagerBuilder
                .getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = tokenUtil.generateToken(userDetails);
        final JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        // 保存在线信息
        onlineUserService.save(jwtUser, token, request);
        // 返回 token 与 用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("userInfo", jwtUser);
        }};
        if (singleLogin) {
            //踢掉之前已经登录的token
            onlineUserService.checkLoginOnUser(username, token);
        }
        return R.ok(authInfo);
    }

    @GetMapping(value = "/code")
    public R<Object> getCode() {
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        String result = "";
        try {
            result = new Double(Double.parseDouble(captcha.text())).intValue() + "";
        } catch (Exception e) {
            result = captcha.text();
        }
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        // 保存
        redisUtils.set(uuid, result, expiration, TimeUnit.MINUTES);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(3) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};

        CeaMSysUserDTO admin = ceamSysUserService.loadUserInfo("admin");
        imgResult.put("attention", admin.getAttention());

        return R.ok(imgResult);
    }

    @GetMapping(value = "/info")
    public R<Object> getUserInfo() {
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        return R.ok(jwtUser);
    }

    @GetMapping(value = "/logout")
    public R logout(HttpServletRequest request) {
        onlineUserService.logout(tokenUtil.getToken(request));
        return R.ok();
    }
}
