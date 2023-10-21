package com.ceam.mall.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CeaM
 * 2023/02/15 13:49
 **/
@Data
public class CeamHomeMenuDTO {

    private Long id;

    private String menuName;

    private String component;

    private Boolean isShow;

    private String icon;

    private Boolean deleted;

    private Long addBy;

    private LocalDateTime addTime;

    private LocalDateTime updTime;

    private Long updBy;

}
