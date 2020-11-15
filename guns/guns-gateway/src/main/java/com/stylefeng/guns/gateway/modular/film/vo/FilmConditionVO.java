package com.stylefeng.guns.gateway.modular.film.vo;

import com.stylefeng.guns.api.model.film.vo.CatVO;
import com.stylefeng.guns.api.model.film.vo.SourceVO;
import com.stylefeng.guns.api.model.film.vo.YearVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2020/11/15 17:00
 */
@Data
public class FilmConditionVO implements Serializable {

    private List<CatVO> catInfo;

    private List<SourceVO> sourceInfo;

    private List<YearVO> yearInfo;
}
