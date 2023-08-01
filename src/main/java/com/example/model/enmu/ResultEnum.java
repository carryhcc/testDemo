package com.example.model.enmu;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/29 09:55
 */
public enum ResultEnum {

    /**
     * 成功
     */
    SUCCESS(200, "success"),
    /**
     * 失败
     */
    ERROR(500, "error"),
    /**
     * 传参出错
     */
    PARAM_ERROR(501, "params error"),
    /**
     * 必传参数丢失
     */
    PARAM_MISS_ERROR(502, "param miss"),
    /**
     * 记录不存在
     */
    RECORD_NOT_EXIST(503, "record is not exist!"),
    /**
     * 空指针异常
     */
    NULL_POINTER_EXCEPTION(504, "null pointer exception!");

    private final Integer code;

    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
