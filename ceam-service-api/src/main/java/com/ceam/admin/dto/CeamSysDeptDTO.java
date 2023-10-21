package com.ceam.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author CeaM
 * 2023/01/29 19:54
 **/
@Data
public class CeamSysDeptDTO {

    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级部门
     */
    private Long pid;

    /**
     * 状态
     */
    private Boolean enabled;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updTime;

    private Boolean deleted;

    private List<CeamSysDeptDTO> children;

    public String getLabel() {
        return name;
    }
}
