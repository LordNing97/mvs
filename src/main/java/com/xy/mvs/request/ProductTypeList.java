package com.xy.mvs.request;

import com.xy.mvs.model.ProductType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/6 10:07
 * @Version 1.0
 */
@Getter
@Builder
@ToString
public class ProductTypeList {

    List<ProductType> productTypeList;
    int total;
    int page;

}
