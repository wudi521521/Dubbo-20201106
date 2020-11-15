package com.stylefeng.guns.gateway.modular.film.vo;

import com.stylefeng.guns.api.model.film.vo.BannerVO;
import com.stylefeng.guns.api.model.film.vo.FilmInfoVO;
import com.stylefeng.guns.api.model.film.vo.FilmVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/11 10:03
 */
@Data
public class FilmIndexVO implements Serializable {

    //获取banner信息
    private List<BannerVO> banners;

    //获取正在热映电影
    private FilmVO hotFilms;

    //获取即将上映电影
    private FilmVO soonFilms;

    //获取票房排行
    private List<FilmInfoVO> boxRanking;

    //获取前一百
    private List<FilmInfoVO> top100;

    //最受欢迎
    private List<FilmInfoVO> expectRanking;
}
