package com.xy.mvs.mapper;

import com.xy.mvs.model.ProductType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    int countProductType(@Param("name") String name, @Param("type") Integer type);

    /**
     * 分页获取产品类型
     * @param name
     * @param page
     * @param size
     * @return
     */
    List<ProductType> getProductTypeList(@Param("name") String name, @Param("type") Integer type, @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 根据id获取产品类型
     * @param id
     * @return
     */
    ProductType getById(Integer id);

    /**
     * 修改产品类型
     * @param productType
     * @return
     */
    int modifyProductType(ProductType productType);

    /**
     * 删除产品类型
     * @param id
     * @return
     */
    int deleteProductType(String id);

    /**
     * 根据类型获取产品
     * @param type
     * @return
     */
    List<ProductType> getProductTypeByType(Integer type);

}
