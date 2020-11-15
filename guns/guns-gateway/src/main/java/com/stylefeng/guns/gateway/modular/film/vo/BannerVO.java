package com.stylefeng.guns.gateway.modular.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/15 15:12
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
