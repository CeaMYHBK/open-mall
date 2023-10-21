package com.ceam.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author CeaM
 * 2023/01/28 13:14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUser {

    private String userName;

    private String nickName;

    private String browser;

    private String ip;

    private String address;

    private String key;

    private Date loginTime;


}

