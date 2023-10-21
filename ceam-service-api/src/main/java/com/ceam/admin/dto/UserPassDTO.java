package com.ceam.admin.dto;

import lombok.Data;

/**
 * @author CeaM
 * 2023/04/05 20:40
 **/
@Data
public class UserPassDTO {

    private String oldPass;
    private String newPass;
}
