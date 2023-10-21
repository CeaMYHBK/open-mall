package com.ceam.admin.controller;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ceam.admin.dto.CeaMSysUserDTO;
import com.ceam.admin.dto.UserPassDTO;
import com.ceam.admin.entity.CeamSysUser;
import com.ceam.admin.service.ICeamSysUserService;
import com.ceam.admin.vo.CeaMSysUserVO;
import com.ceam.common.exception.ServiceException;
import com.ceam.common.utils.BeanCopyUtil;
import com.ceam.common.utils.R;
import com.ceam.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

/**
 * @author CeaM
 * 2023/01/27 10:31
 **/
@Slf4j
@RestController
@RequestMapping("/api/users")
public class CeamSysUserController {

    private final ICeamSysUserService ceamSysUserService;
    private final PasswordEncoder passwordEncoder;
    @Value("${rsa.private_key}")
    private String privateKey;

    public CeamSysUserController(ICeamSysUserService ceamSysUserService,
                                 PasswordEncoder passwordEncoder) {
        this.ceamSysUserService = ceamSysUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public R page(CeaMSysUserDTO data) {
        IPage<CeaMSysUserVO> ceaMSysUserVOIPage = ceamSysUserService.pageCeaMSysUserVO(data);
        return R.ok(ceaMSysUserVOIPage);
    }

    @PostMapping("register")
    public R<Object> register(@RequestBody CeaMSysUserDTO data) {
        // 默认密码 123456
        data.setPassword(passwordEncoder.encode("123456"));
        boolean add = ceamSysUserService.add(data);
        return R.ok(add);
    }

    @PostMapping
    public R<Object> add(@RequestBody CeaMSysUserDTO data) {
        // 默认密码 123456
        data.setPassword(passwordEncoder.encode("123456"));
        boolean add = ceamSysUserService.add(data);
        return R.ok(add);
    }

    @DeleteMapping
    public R<Object> remove(@RequestBody Set<Long> ids) {
        ceamSysUserService.remove(ids);
        return R.ok("成功");
    }

    @PostMapping(value = "/updatePass")
    public R<Object> updatePass(@RequestBody UserPassDTO passDTO) {

        // 密码解密
        RSA rsa = new RSA(privateKey, null);
        String oldPass = new String(rsa.decrypt(passDTO.getOldPass(), KeyType.PrivateKey));
        String newPass = new String(rsa.decrypt(passDTO.getNewPass(), KeyType.PrivateKey));
        CeaMSysUserDTO sysUserDTO = ceamSysUserService.loadUserInfo(SecurityUtils.getUsername());
        if (!passwordEncoder.matches(oldPass, sysUserDTO.getPassword())) {
            throw new ServiceException("修改失败，旧密码错误");
        }
        if (passwordEncoder.matches(newPass, sysUserDTO.getPassword())) {
            throw new ServiceException("新密码不能与旧密码相同");
        }
        ceamSysUserService.updatePass(sysUserDTO.getUsername(), passwordEncoder.encode(newPass));
        return R.ok();
    }

    @PutMapping(value = "center")
    public R<Object> center(@RequestBody CeaMSysUserDTO data) {

        CeaMSysUserDTO userDto = ceamSysUserService.loadUserInfo(SecurityUtils.getUsername());
        if (!data.getId().equals(userDto.getId())) {
            throw new ServiceException("不能修改他人资料");
        }

        CeamSysUser ceamSysUser = BeanCopyUtil.copyProperties(data, CeamSysUser.class);
        ceamSysUserService.saveOrUpdate(ceamSysUser);
        return R.ok();
    }

    @PostMapping(value = "/updateAvatar")
    public R<Object> updateAvatar(@RequestParam MultipartFile file) {

        ceamSysUserService.updateAvatar(file);
        return R.ok();
    }
}
