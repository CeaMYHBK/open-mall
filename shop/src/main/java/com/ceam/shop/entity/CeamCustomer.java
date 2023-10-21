package com.ceam.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author CeaM
 * @since 2023-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CeamCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员码
     */
    private String customerCode;

    /**
     * 系统类型（默认 default, 其他按照SYSTEM_TYPE常量类型）
     */
    private String systemType;

    /**
     * 支付宝openid
     */
    private String alipayOpenid;

    /**
     * 微信openid
     */
    private String weixinOpenid;

    /**
     * 微信unionid
     */
    private String weixinUnionid;

    /**
     * 微信web用户openid
     */
    private String weixinWebOpenid;

    /**
     * 百度openid
     */
    private String baiduOpenid;

    /**
     * 百度openid
     */
    private String toutiaoOpenid;

    /**
     * 头条unionid
     */
    private String toutiaoUnionid;

    /**
     * QQopenid
     */
    private String qqOpenid;

    /**
     * QQunionid
     */
    private String qqUnionid;

    /**
     * 快手openid
     */
    private String kuaishouOpenid;

    /**
     * 状态（0正常, 1禁止发言, 2禁止登录）
     */
    private Integer status;

    /**
     * 配合密码加密串
     */
    private String salt;

    /**
     * 登录密码
     */
    private String pwd;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 电子邮箱（最大长度60个字符）
     */
    private String email;

    /**
     * 性别（0保密，1女，2男）
     */
    private Integer sex;

    /**
     * 用户头像地址
     */
    private String avatar;

    /**
     * 所在省
     */
    private String province;

    /**
     * 所在市
     */
    private String city;

    /**
     * 所在区
     */
    private String county;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 有效积分
     */
    private Integer integral;

    /**
     * 锁定积分
     */
    private Integer lockingIntegral;

    /**
     * 推荐人用户id
     */
    private Integer referrer;

    /**
     * 是否已删除
     */
    private Boolean deleted;

    /**
     * 是否已注销
     */
    private Boolean isLogout;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;

    private Integer buyTimes;

    private BigDecimal balance;


}
