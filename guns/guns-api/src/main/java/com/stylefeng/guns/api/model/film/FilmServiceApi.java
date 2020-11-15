package com.stylefeng.guns.api.model.film;

import com.stylefeng.guns.api.model.film.vo.*;

import java.util.List;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/11 10:50
 */
public interface FilmServiceApi {

    /**
     * 获取banner信息
     */
    List<BannerVO> getBanners();

    /**
     * 获取正在热映电影
     */
    FilmVO getHotFilms(boolean isLimit, int nums);

    /**
     * /获取即将上映电影【受欢迎程度做排序】
     */
    FilmVO getSoonFilms(boolean isLimit, int nums);

    /**
     * 获取票房排行
     */
    List<FilmInfoVO> getBoxRanking();

    /***
     * 获取人气排行榜
     */

    List<FilmInfoVO> getExpectRanking();

    /**
     * 获取前一百
     */

    List<FilmInfoVO> getTop();

    //=====获取影片条件接口
    //分类条件
    List<CatVO> getCats();

    //获取来源
    List<SourceVO> getSources();

    //获取年代
    List<YearVO> getYears();
}
