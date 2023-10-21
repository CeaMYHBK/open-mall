package com.ceam.mall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author CeaM
 * 2023/02/09 15:14
 **/
@Data
@AllArgsConstructor
public class HomeTab {
    private int current;
    private String tabName;
    Object list;
}
