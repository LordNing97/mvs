package com.xy.mvs.api;

import lombok.Getter;

/**
 * @Author 陈璇
 * @Date 2020/4/2 13:58
 * @Version 1.0
 **/
@Getter
public class Result<T> {

    private String msg;
    private int code;
    private T data;
    private long responseTime; //当前时间戳

    public Result(ResultBuilder<T> builder) {
        this.msg = builder.msg;
        this.code = builder.code;
        this.data = builder.data;
        this.responseTime = builder.responseTime;
    }

//    public static <T> ResultBuilder<T> builder() {
//        return new ResultBuilder<>(ResultCode.SUCCESS);
//    }

    public static <T> ResultBuilder<T> builder() {
        return new ResultBuilder<>();
    }

    public static <T> ResultBuilder<T> builder(int code, String msg) {
        return new ResultBuilder<>(code, msg);
    }

    public static <T> ResultBuilder<T> builder(ResultCode resultCode) {
        return new ResultBuilder<>(resultCode);
    }

    public static class ResultBuilder<T> {
        private int code;
        private String msg;
        private T data;
        private long responseTime;

        public ResultBuilder() {
            this.msg = "操作成功!";
            this.code = 200;
            this.responseTime = System.currentTimeMillis();
        }

        public ResultBuilder(int code, String msg) {
            this.msg = msg;
            this.code = code;
            this.responseTime = System.currentTimeMillis();
        }

        public ResultBuilder(ResultCode resultCode) {
            this.msg = resultCode.msg;
            this.code = resultCode.code;
            this.responseTime = System.currentTimeMillis();
        }

        public ResultBuilder<T> code(int code) {
            this.code = code;
            return this;
        }

        public ResultBuilder<T>  msg(String msg) {
            this.msg = msg;
            return this;
        }

        public ResultBuilder<T>  data(T data) {
            this.data = data;
            return this;
        }

        public ResultBuilder<T>  responseTime(long responseTime) {
            this.responseTime = responseTime;
            return this;
        }

        public Result<T> build() {
            return new Result<>(this);
        }
    }

}
