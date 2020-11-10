package com.stylefeng.guns.gateway.modular.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author Dillon Wu
 * @Description: 返回结构
 * @date 2020/11/9 16:37
 */
@Data
public class ResponseVO<M> {
    /**
     * 返回状态【0-成功，1-业务失败，999-表示系统异常】
     */
    private int status;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回实体
     */
    private M data;
    private ResponseVO(){}

    /**
     * 成功
     * @param m
     * @param <M>
     * @return
     */
     public static<M> ResponseVO success(M m){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(m);

        return responseVO;
     }
    /**
     * 成功
     * @param msg
     * @param <M>
     * @return
     */
    public static<M> ResponseVO success(String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setMsg(msg);

        return responseVO;
    }

    /**
     * 业务异常
     * @param m
     * @param <M>
     * @return
     */
    public static<M> ResponseVO serviceFail(String m){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setData(m);

        return responseVO;
    }

    /**
     * 服务异常
     * @param m
     * @param <M>
     * @return
     */
    public static<M> ResponseVO appFail(String m){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setData(m);

        return responseVO;
    }






}
