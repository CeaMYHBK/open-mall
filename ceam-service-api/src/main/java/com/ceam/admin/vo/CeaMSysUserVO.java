package com.ceam.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/01/27 15:58
 **/
@Data
public class CeaMSysUserVO {

    private Long jobId;
    private String email;
    private Long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 性别：0 未知， 1男， 1 女
     */
    private Integer sex;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime birthday;

    /**
     * 最近一次登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastLoginTime;

    /**
     * 最近一次登录IP地址
     */
    private String lastLoginIp;

    /**
     * 用户层级 0 普通用户，1 VIP用户，2 区域代理用户
     */
    private Integer userLevel;

    /**
     * 用户昵称或网络名称
     */
    private String nickname;

    /**
     * 用户手机号码
     */
    private String phoneNum;

    /**
     * 用户头像图片
     */
    private String avatar;

    /**
     * 微信登录openid
     */
    private String weixinOpenid;

    private Long shareUserId;

    /**
     * 0 可用, 1 禁用, 2 注销
     */
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    private Boolean deleted;
}
