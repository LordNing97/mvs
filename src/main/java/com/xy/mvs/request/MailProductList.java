package com.xy.mvs.request;

import com.xy.mvs.model.MailProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/9 14:54
 * @Version 1.0
 */
@Getter
@Builder
@ToString
public class MailProductList {

    List<MailProduct> mailProductList;
    int total;
    int page;

}
