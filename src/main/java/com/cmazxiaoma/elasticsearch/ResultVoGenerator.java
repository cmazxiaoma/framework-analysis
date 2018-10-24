package com.cmazxiaoma.elasticsearch;

/**
 * 响应结果生成工具
 */
public class ResultVoGenerator {

    public static ResultVo genSuccessResult() {
        return new ResultVo.Builder()
                .code(ResultCode.SUCCESS.getCode())
                .msg(ResultCode.SUCCESS.getMsg())
                .build();
    }

    public static ResultVo genSuccessResult(Object data) {
        return new ResultVo.Builder()
                .code(ResultCode.SUCCESS.getCode())
                .msg(ResultCode.SUCCESS.getMsg())
                .data(data)
                .build();
    }

    public static ResultVo genFailResult(String message) {
        return new ResultVo.Builder()
                .code(ResultCode.FAIL.getCode())
                .msg(message)
                .build();
    }

    public static ResultVo genCustomResult(ResultCode resultCode) {
        return new ResultVo.Builder()
                .code(resultCode.getCode())
                .msg(resultCode.getMsg())
                .data("null")
                .build();
    }

    public static ResultVo genCustomResult(ResultCode resultCode, String message) {
        return new ResultVo.Builder()
                .code(resultCode.getCode())
                .msg(message)
                .data("null")
                .build();
    }
}
