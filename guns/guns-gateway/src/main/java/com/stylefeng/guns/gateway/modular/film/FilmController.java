package com.stylefeng.guns.gateway.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.model.film.FilmServiceApi;
import com.stylefeng.guns.api.model.film.vo.CatVO;
import com.stylefeng.guns.api.model.film.vo.SourceVO;
import com.stylefeng.guns.api.model.film.vo.YearVO;
import com.stylefeng.guns.gateway.modular.film.vo.FilmConditionVO;
import com.stylefeng.guns.gateway.modular.film.vo.FilmIndexVO;
import com.stylefeng.guns.gateway.modular.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/11 9:45
 */
@Slf4j
@RestController
@RequestMapping("/film")
public class FilmController {

    private static final String IMG_PRE = "http://img.meetingshop.cn/";

    @Reference(interfaceClass = FilmServiceApi.class)
    private FilmServiceApi filmServiceApi;

    /**
     * 首页信息
     * API网关:
     * 1:功能聚合[API聚合]
     * 好处:
     * 1:六个接口，一次请求，同时时刻节省五次HTTP请求
     * 2:同一个接口对外暴露，降低了前后端分离开发的难度和复杂度
     * 坏处:
     * 1:一次获取数据多，容易出现问题
     *
     * @return
     */
    @GetMapping("/index")
    public ResponseVO getIndex() {
        FilmIndexVO filmIndexVO = new FilmIndexVO();
        //获取banner信息
        filmIndexVO.setBanners(filmServiceApi.getBanners());
        //获取正在热映电影
        filmIndexVO.setHotFilms(filmServiceApi.getHotFilms(true, 8));
        //获取即将上映电影
        filmIndexVO.setSoonFilms(filmServiceApi.getSoonFilms(true, 8));

        //获取票房排行
        filmIndexVO.setBoxRanking(filmServiceApi.getBoxRanking());
        //获取受欢迎
        filmIndexVO.setExpectRanking(filmServiceApi.getExpectRanking());
        //获取前一百
        filmIndexVO.setTop100(filmServiceApi.getTop());

        return ResponseVO.success(IMG_PRE, filmIndexVO);
    }

    @GetMapping("/conditions")
    public ResponseVO getConditionList( String catId,
                                       String sourceId,
                                       String yearId) {
        FilmConditionVO filmConditionVO = new FilmConditionVO();


        //标识位
        boolean flag = false;
        //类型集合
        List<CatVO> cats = filmServiceApi.getCats();
        List<CatVO> catResult = new ArrayList<>();
        CatVO cat = null;
        for (CatVO catVO : cats) {
            //判断集合是否存在catId,如果存在，则将对应的实体变成active状态
            if (catVO.getCatId().equals("99")) {
                cat = catVO;
                continue;
            }
            if (catVO.getCatId().equals(catId)) {
                flag = true;
                catVO.setActive(true);
            }

            catResult.add(catVO);

        }
        //如果不存在，则默认将全部变为Active状态
        if (!flag) {
            cat.setActive(true);
            catResult.add(cat);
        } else {
            cat.setActive(false);
            catResult.add(cat);
        }
        //片源集合

        //类型集合
        flag = false;
        List<SourceVO> sources = filmServiceApi.getSources();
        List<SourceVO> sourceResult = new ArrayList<>();
        SourceVO source = null;
        for (SourceVO sourceVO : sources) {
            //判断集合是否存在catId,如果存在，则将对应的实体变成active状态
            if (sourceVO.getSourceId().equals("99")) {
                source = sourceVO;
                continue;
            }


            sourceResult.add(sourceVO);

        }
        //如果不存在，则默认将全部变为Active状态
        if (!flag) {
            source.setActive(true);
            sourceResult.add(source);
        }else {
            sourceResult.add(source);
        }

        //年代集合
        flag = false;
        List<YearVO> years = filmServiceApi.getYears();
        List<YearVO> yearResult = new ArrayList<>();
        YearVO year = null;
        for (YearVO yearVO : years) {
            //判断集合是否存在catId,如果存在，则将对应的实体变成active状态
            if (yearVO.getYearId().equals("99")) {
                year = yearVO;
                continue;
            }
            if (yearVO.getYearId().equals(yearId)) {
                flag = true;
                yearVO.setActive(true);
            }

            yearResult.add(yearVO);

        }
        //如果不存在，则默认将全部变为Active状态
        if (!flag) {
            year.setActive(true);
            yearResult.add(year);
        } else {
            yearResult.add(year);
        }
        filmConditionVO.setCatInfo(catResult);
        filmConditionVO.setSourceInfo(sourceResult);
        filmConditionVO.setYearInfo(yearResult);
        return ResponseVO.success(filmConditionVO);
    }
}
