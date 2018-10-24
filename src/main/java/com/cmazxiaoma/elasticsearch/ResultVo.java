package com.cmazxiaoma.elasticsearch;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 统一API响应结果封装
 */
@Data
public class ResultVo {
    private final Integer code;
    private final String msg;
    private final Object data;

    public static class Builder {
        private int code;
        private String msg;
        private Object data;

        public Builder() {

        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public ResultVo build() {
            return new ResultVo(this);
        }
    }

    private ResultVo(Builder builder) {
        this.code = builder.code;
        this.msg = builder.msg;
        this.data = builder.data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
