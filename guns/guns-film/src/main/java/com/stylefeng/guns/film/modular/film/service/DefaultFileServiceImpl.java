package com.stylefeng.guns.film.modular.film.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.model.film.FilmServiceApi;
import com.stylefeng.guns.api.model.film.vo.*;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.film.common.persistence.dao.*;
import com.stylefeng.guns.film.common.persistence.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dillon Wu
 * @Description: 电影模块
 * @date 2020/11/11 11:39
 */
@Slf4j
@Service(interfaceClass = FilmServiceApi.class)
@RestController
public class DefaultFileServiceImpl implements FilmServiceApi {

    @Autowired
    private MoocBannerTMapper moocBannerTMapper;

    @Autowired
    private MoocFilmTMapper moocFilmTMapper;

    @Autowired
    private MoocCatDictTMapper moocCatDictTMapper;

    @Autowired
    private MoocYearDictTMapper moocYearDictTMapper;

    @Autowired
    private MoocSourceDictTMapper moocSourceDictTMapper;

    @Autowired
    private MoocFilmInfoTMapper moocFilmInfoTMapper;

    @Autowired
    private MoocActorTMapper moocActorTMapper;

    @Override
    public List<BannerVO> getBanners() {
        List<BannerVO> result = new ArrayList<>();
        List<MoocBannerT> moocBanners = moocBannerTMapper.selectList(null);
        for (MoocBannerT moocBannerT : moocBanners) {
            BannerVO bannerVO = new BannerVO();
            bannerVO.setBannerId(String.valueOf(moocBannerT.getUuid()));
            bannerVO.setBannerUrl(moocBannerT.getBannerUrl());
            bannerVO.setBannerAddress(moocBannerT.getBannerAddress());
            result.add(bannerVO);
        }
        return result;
    }

    @Override
    public FilmVO getHotFilms(boolean isLimit, int nums) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfoVO> filmInfoVOS = new ArrayList<>();
        //热映影片的限制条件
        EntityWrapper<MoocFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status", "1");
        //判断是否是首页需要的内容
        if (isLimit) {
            //如果是,则限制条数，限制内容为热映影片
            Page<MoocFilmT> page = new Page<MoocFilmT>(1, nums);
            List<MoocFilmT> moocFilms = moocFilmTMapper.selectPage(page, entityWrapper);
            //组织filmInfos
            filmInfoVOS = getFilmInfos(moocFilms);
            filmVO.setFilmNumber(moocFilms.size());
            filmVO.setFilmInfoVOS(filmInfoVOS);
        } else {

        }

        return filmVO;
    }

    @Override
    public FilmVO getSoonFilms(boolean isLimit, int nums) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfoVO> filmInfoVOS = new ArrayList<>();
        //热映影片的限制条件,影片状态,1-正在热映，2-即将上映，3-经典影片
        EntityWrapper<MoocFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status", "2");
        //判断是否是首页需要的内容
        if (isLimit) {
            //如果是,则限制条数，限制内容为热映影片
            Page<MoocFilmT> page = new Page<MoocFilmT>(1, nums);
            List<MoocFilmT> moocFilms = moocFilmTMapper.selectPage(page, entityWrapper);
            //组织filmInfos
            filmInfoVOS = getFilmInfos(moocFilms);
            filmVO.setFilmNumber(moocFilms.size());
            filmVO.setFilmInfoVOS(filmInfoVOS);
        } else {

        }

        return filmVO;
    }

    @Override
    public List<FilmInfoVO> getBoxRanking() {
        //条件->正在上映的，票房前10名
        EntityWrapper<MoocFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status", "1");
        //默认为倒序排序
        Page<MoocFilmT> page = new Page<>(1, 10, "film_box_office");

        List<MoocFilmT> moocFilms = moocFilmTMapper.selectPage(page, entityWrapper);
        List<FilmInfoVO> filmInfos = getFilmInfos(moocFilms);

        return filmInfos;
    }

    @Override
    public List<FilmInfoVO> getExpectRanking() {
        //条件->即将上映的，预售前10面
        EntityWrapper<MoocFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status", "2");
        //默认为倒序排序
        Page<MoocFilmT> page = new Page<>(1, 10, "film_box_office");

        List<MoocFilmT> moocFilms = moocFilmTMapper.selectPage(page, entityWrapper);
        List<FilmInfoVO> filmInfos = getFilmInfos(moocFilms);

        return filmInfos;
    }

    @Override
    public List<FilmInfoVO> getTop() {
        //条件->正在上映的，前10名
        EntityWrapper<MoocFilmT> entityWrapper = new  EntityWrapper<>();
        entityWrapper.eq("film_status", "1");
        //默认为倒序排序
        Page<MoocFilmT> page = new Page<>(1, 10, "film_score");

        List<MoocFilmT> moocFilms = moocFilmTMapper.selectPage(page, entityWrapper);
        List<FilmInfoVO> filmInfos = getFilmInfos(moocFilms);

        return filmInfos;
    }

    @Override
    public List<CatVO> getCats() {
        List<CatVO> cats = new ArrayList<>();
        //查询实体对象--MoocCatDictT
        List<MoocCatDictT> moocCats = moocCatDictTMapper.selectList(null);
        //将实体对象转化业务对象
        for (MoocCatDictT moocCatDictT:moocCats){
            CatVO catVO = new CatVO();
            catVO.setCatId(moocCatDictT.getUuid()+"");
            catVO.setCatName(moocCatDictT.getShowName());
            cats.add(catVO);
        }
        return cats;
    }

    @Override
    public List<SourceVO> getSources() {
        List<SourceVO> sources = new ArrayList<>();
        //查询实体对象--MoocCatDictT
        List<MoocSourceDictT> moocSources = moocSourceDictTMapper.selectList(null);
        //将实体对象转化业务对象
        for (MoocSourceDictT moocsourceDictT:moocSources){
            SourceVO vo = new SourceVO();
            vo.setSourceId(moocsourceDictT.getUuid()+"");
            vo.setSourceName(moocsourceDictT.getShowName());
            sources.add(vo);
        }
        return sources;
    }



    @Override
    public List<YearVO> getYears() {
        List<YearVO> years = new ArrayList<>();
        //查询实体对象--MoocCatDictT
        List<MoocYearDictT> moocYears = moocYearDictTMapper.selectList(null);
        //将实体对象转化业务对象
        for (MoocYearDictT moocYearDictT:moocYears){
            YearVO vo = new YearVO();
            vo.setYearId(moocYearDictT.getUuid()+"");
            vo.setYearName(moocYearDictT.getShowName());
            years.add(vo);
        }
        return years;
    }

    /**
     * 整合数据 filmInfos
     *
     * @param moocFilms
     * @return
     */
    private List<FilmInfoVO> getFilmInfos(List<MoocFilmT> moocFilms) {
        List<FilmInfoVO> filmInfos = new ArrayList<>();
        for (MoocFilmT moocFilmT : moocFilms) {
            FilmInfoVO filmInfo = new FilmInfoVO();
            filmInfo.setScore(moocFilmT.getFilmScore());
            filmInfo.setImgAddress(moocFilmT.getImgAddress());
            filmInfo.setFilmType(moocFilmT.getFilmType());
            filmInfo.setFilmScore(moocFilmT.getFilmScore());
            filmInfo.setFilmName(moocFilmT.getFilmName());
            filmInfo.setFilmId(moocFilmT.getUuid() + "");
            filmInfo.setExpectNum(moocFilmT.getFilmPresalenum());
            filmInfo.setBoxNum(moocFilmT.getFilmBoxOffice());
            filmInfo.setShowTime(DateUtil.getDay(moocFilmT.getFilmTime()));
            //整合数据
            filmInfos.add(filmInfo);
        }
        return filmInfos;
    }
}
