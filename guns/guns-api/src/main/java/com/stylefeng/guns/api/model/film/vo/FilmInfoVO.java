package com.stylefeng.guns.api.model.film.vo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/11 10:04
 */
@Data
public class FilmInfoVO implements Serializable {

    /**
     * 电影id
     */
    private String filmId;

    /**
     * 电影类型
     */
    private int filmType;

    /**
     * 图片地址
     */
    private String imgAddress;

    /**
     * 电影名称
     */
    private String filmName;

    /**
     * 电影评分
     */
    private String filmScore;

    /**
     *期待人数
     */
    private int expectNum;
    /**
     * 表现时间
     */
    private String showTime;

    private int boxNum;

    private String score;


}
