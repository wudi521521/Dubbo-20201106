package com.stylefeng.guns.api.model.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Dillon Wu
 * @Description: banner 返给前端数据
 * @date 2020/11/11 9:55
 */
@Data
public class BannerVO implements Serializable {

    /**
     * banner id
     */
    private String bannerId;

    /**
     * banner 地址
     */
    private String bannerAddress;

    /**
     * banner 路径
     */
    private String bannerUrl;
}
