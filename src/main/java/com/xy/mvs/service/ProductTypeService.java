package com.xy.mvs.service;

import com.xy.mvs.mapper.ProductTypeMapper;
import com.xy.mvs.model.ProductType;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @Author 陈璇
 * @Date 2021/1/4 17:35
 * @Version 1.0
 */
@Service
public class ProductTypeService {

    @Resource
    private ProductTypeMapper productTypeMapper;

    /**
     * 保存产品类型
     * @param productType
     * @return
     */
    public boolean saveProductType(ProductType productType){
        productType.setIsDel(0);
        return productTypeMapper.saveProductType(productType) > 0;
    }

}
