package com.ceam.token.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ceam.admin.vo.JwtUser;
import com.ceam.admin.vo.OnlineUser;
import com.ceam.common.utils.EncryptUtils;
import com.ceam.common.utils.RedisUtils;
import com.ceam.common.utils.StringUtils;
import com.ceam.token.jwt.SecurityProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author CeaM
 * 2023/01/19 22:49
 **/
@Service
@Slf4j
@AllArgsConstructor
public class OnlineUserService {

    private SecurityProperties properties;
    private RedisUtils redisUtils;

    /**
     * 保存在线用户信息
     * @param jwtUser /
     * @param token /
     * @param request /
     */
    public void save(JwtUser jwtUser, String token, HttpServletRequest request) {
        String ip = StringUtils.getIp(request);
        String browser = StringUtils.getBrowser(request);
        String address = StringUtils.getCityInfo(ip);
        OnlineUser onlineUser = null;
        try {
            onlineUser = new OnlineUser(jwtUser.getUsername(), jwtUser.getNickname(),
                    browser, ip, address, EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }


        redisUtils.set(properties.getOnlineKey() + token, onlineUser
                , properties.getValidity() / 1000);
        log.info("Online......>>>>>{}", redisUtils.get(properties.getOnlineKey() + token));
    }

    /**
     * 查询全部数据，不分页
     * @param filter /
     * @return /
     */
    public List<OnlineUser> getAll(String filter, int type) {
        List<String> keys = null;
        if (type == 1) {
            keys = redisUtils.scan("m-online-token*");
        } else {
            keys = redisUtils.scan(properties.getOnlineKey() + "*");
        }


        Collections.reverse(keys);
        List<OnlineUser> onlineUsers = new ArrayList<>();
        for (String key : keys) {
            OnlineUser onlineUser;
            Object obj = redisUtils.get(key);
            if (obj instanceof OnlineUser) {
                onlineUser = (OnlineUser) obj;
            } else if (obj instanceof JSONObject) {
                onlineUser = JSONObject.parseObject(JSONObject.toJSONString(obj), OnlineUser.class);
            } else if (obj instanceof String) {
                onlineUser = JSONObject.parseObject((String) obj, OnlineUser.class);
            } else {
                continue;
            }
            onlineUsers.add(onlineUser);
        }
        onlineUsers.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUsers;
    }

    /**
     * 踢出用户
     * @param key /
     * @throws Exception /
     */
    public void kickOut(String key) throws Exception {
        key = properties.getOnlineKey() + EncryptUtils.desDecrypt(key);
        redisUtils.del(key);

    }

    /**
     * 踢出移动端用户
     * @param key /
     * @throws Exception /
     */
    public void kickOutT(String key) throws Exception {

        String keyt = "m-online-token" + EncryptUtils.desDecrypt(key);
        redisUtils.del(keyt);

    }

    /**
     * 退出登录
     * @param token /
     */
    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
        redisUtils.del(key);
    }

    /**
     * 查询用户
     * @param key /
     * @return /
     */
    public OnlineUser getOne(String key) {
        log.info("????logout>>>>>>>>{}", redisUtils.getString(key));
        return JSON.parseObject(redisUtils.getString(key), OnlineUser.class);
    }

    /**
     * 检测用户是否在之前已经登录，已经登录踢下线
     * @param userName 用户名
     */
    public void checkLoginOnUser(String userName, String igoreToken) {
        List<OnlineUser> onlineUsers = getAll(userName, 0);
        if (onlineUsers == null || onlineUsers.isEmpty()) {
            return;
        }
        for (OnlineUser onlineUser : onlineUsers) {
            if (onlineUser.getUserName().equals(userName)) {
                try {
                    String token = EncryptUtils.desDecrypt(onlineUser.getKey());
                    if (StringUtils.isNotBlank(igoreToken) && !igoreToken.equals(token)) {
                        this.kickOut(onlineUser.getKey());
                    } else if (StringUtils.isBlank(igoreToken)) {
                        this.kickOut(onlineUser.getKey());
                    }
                } catch (Exception e) {
                    log.error("checkUser is error", e);
                }
            }
        }
    }

}
