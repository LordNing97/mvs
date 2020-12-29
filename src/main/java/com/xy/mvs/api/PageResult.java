package com.xy.mvs.api;

import lombok.Getter;

import java.util.List;

/**
 * @Author 陈璇
 * @Date 2020/4/2 13:57
 * @Version 1.0
 **/
@Getter
public class PageResult<T> {

    private String msg;
    private int code;
    private long total; //数据总数
    private Integer page;   //当前页码
    private List<T> data;
    private long responseTime; //当前时间戳

    public PageResult(PageResultBuilder<T> builder) {
        this.msg = builder.msg;
        this.code = builder.code;
        this.total = builder.total;
        this.page = builder.page;
        this.data = builder.data;
        this.responseTime = builder.responseTime;
    }

    public static <T> PageResultBuilder<T> builder() {
        return new PageResultBuilder<>();
    }

    public static class PageResultBuilder<T> {
        private int code;
        private String msg;
        private long total;
        private Integer page;
        private List<T> data;
        private long responseTime;

        public PageResultBuilder() {
            this.msg = "操作成功!";
            this.code = 200;
            this.responseTime = System.currentTimeMillis();
        }

        public PageResultBuilder<T> code(int code) {
            this.code = code;
            return this;
        }

        public PageResultBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public PageResultBuilder<T> data(List<T> data) {
            this.data = data;
            return this;
        }

        public PageResultBuilder<T> total(long total) {
            this.total = total;
            return this;
        }

        public PageResultBuilder<T> page(Integer page) {
            this.page = page;
            return this;
        }

        public PageResultBuilder<T> responseTime(long responseTime) {
            this.responseTime = responseTime;
            return this;
        }

        public PageResult<T> build() {
            return new PageResult<>(this);
        }
    }

}
