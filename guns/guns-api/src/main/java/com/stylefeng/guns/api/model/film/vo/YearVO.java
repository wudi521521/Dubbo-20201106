package com.stylefeng.guns.api.model.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/15 16:39
 */
@Data
public class YearVO implements Serializable {

    private String yearId;

    private String yearName;

    private boolean isActive;
}
