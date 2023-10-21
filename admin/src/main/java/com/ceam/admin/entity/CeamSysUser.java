package com.ceam.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author CeaM
 * @since 2023-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CeamSysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long deptId;
    private String email;
    private Long jobId;
    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 性别：0 未知， 1男， 1 女
     */
    private Integer sex;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 最近一次登录时间
     */
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

    private LocalDateTime addTime;

    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    private Boolean deleted;

    private Boolean attention;


}
