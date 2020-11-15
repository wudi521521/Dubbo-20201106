package com.stylefeng.guns.api.model.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/11 10:03
 */
@Data
public class FilmVO implements Serializable {

    /**
     * 电影number
     */
    private int filmNumber;

    private List<FilmInfoVO> filmInfoVOS;
}
