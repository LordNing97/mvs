package com.xy.mvs.service;

import com.xy.mvs.mapper.ProductTypeMapper;
import com.xy.mvs.model.ProductType;
import com.xy.mvs.request.ProductTypeList;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 分页获取产品类型
     * @param name
     * @param page
     * @param size
     * @return
     */
    public ProductTypeList getProductTypeList(String name, Integer type, Integer page, Integer size){
        List<ProductType> productTypeList = productTypeMapper.getProductTypeList(name, type, (page - 1) * size, size);
        return ProductTypeList.builder()
                .productTypeList(productTypeList)
                .total(productTypeMapper.countProductType(name, type))
                .page(page)
                .build();

    }

    /**
     * 根据id获取产品类型
     * @param id
     * @return
     */
    public ProductType getById(Integer id){
        return productTypeMapper.getById(id);
    }

    /**
     * 修改产品类型
     * @param productType
     * @return
     */
    public boolean modifyProductType(ProductType productType){
        return productTypeMapper.modifyProductType(productType) > 0;
    }

    /**
     * 删除产品类型
     * @param id
     * @return
     */
    public boolean deleteProductType(String id){
        return productTypeMapper.deleteProductType(id) > 0;
    }

    /**
     * 根据类型获取产品
     * @param type
     * @return
     */
    public List<ProductType> getProductTypeByType(Integer type){
        return productTypeMapper.getProductTypeByType(type);
    }

}
