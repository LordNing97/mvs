package com.xy.mvs.mapper;

import com.xy.mvs.model.ProductType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 陈璇
 * @Date 2021/1/4 17:34
 * @Version 1.0
 */
@Mapper
public interface ProductTypeMapper {

    /**
     * 保存产品类型
     * @param productType
     * @return
     */
    int saveProductType(ProductType productType);

}
