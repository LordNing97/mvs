package com.xy.mvs.mapper;

import com.xy.mvs.model.MailProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/9 13:58
 * @Version 1.0
 */
@Mapper
public interface MailProductMapper {

    /**
     * 保存商城产品
     * @param mailProduct
     * @return
     */
    int saveMailProduct(MailProduct mailProduct);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    MailProduct getById(Integer id);

    /**
     * 修改商城产品
     * @param mailProduct
     * @return
     */
    int modifyMailProduct(MailProduct mailProduct);

    int countMailProduct(@Param("name") String name, @Param("productTypeId") Integer productTypeId, @Param("status") Integer status);

    /**
     * 分页获取商城产品
     * @param name
     * @param productTypeId
     * @param status
     * @param page
     * @param size
     * @return
     */
    List<MailProduct> getMailProductList(@Param("name") String name, @Param("productTypeId") Integer productTypeId, @Param("status") Integer status,
                                         @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 删除商城产品
     * @param id
     * @return
     */
    int deleteMailProduct(Integer id);

}
