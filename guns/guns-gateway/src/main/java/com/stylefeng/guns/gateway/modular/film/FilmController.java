package com.stylefeng.guns.gateway.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.model.film.FilmServiceApi;
import com.stylefeng.guns.gateway.modular.film.vo.FilmIndexVO;
import com.stylefeng.guns.gateway.modular.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/11 9:45
 */
@Slf4j
@RestController
@RequestMapping("/film")
public class FilmController {

    private static final String IMG_PRE="http://img.meetingshop.cn/";

    @Reference(interfaceClass = FilmServiceApi.class)
    private FilmServiceApi filmServiceApi;

    /**
     * 首页信息
     * API网关:
     * 1:功能聚合[API聚合]
     * 好处:
     *    1:六个接口，一次请求，同时时刻节省五次HTTP请求
     *    2:同一个接口对外暴露，降低了前后端分离开发的难度和复杂度
     * 坏处:
     *     1:一次获取数据多，容易出现问题
     * @return
     */
    @GetMapping("/index")
    public ResponseVO getIndex(){
        FilmIndexVO filmIndexVO = new FilmIndexVO();
        //获取banner信息
        filmIndexVO.setBanners(filmServiceApi.getBanners());
        //获取正在热映电影
        filmIndexVO.setHotFilms(filmServiceApi.getHotFilms(true,8));
        //获取即将上映电影
        filmIndexVO.setSoonFilms(filmServiceApi.getSoonFilms(true,8));

        //获取票房排行
        filmIndexVO.setBoxRanking(filmServiceApi.getBoxRanking());

        //获取受欢迎
        filmIndexVO.setExpectRanking(filmServiceApi.getExpectRanking());
        //获取前一百
        filmIndexVO.setTop100(filmServiceApi.getTop());

        return ResponseVO.success(IMG_PRE,filmIndexVO);
    }
}
