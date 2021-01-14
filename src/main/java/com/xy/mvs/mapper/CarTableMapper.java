package com.xy.mvs.mapper;

import com.xy.mvs.model.CarTable;
import com.xy.mvs.request.CarTableAndProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/14 11:28
 * @Version 1.0
 */
@Mapper
public interface CarTableMapper {

    /**
     * 保存购物车
     * @param carTable
     * @return
     */
    int saveCarTable(CarTable carTable);

    /**
     * 根据ID获取购物车
     * @param id
     * @return
     */
    CarTable getById(Integer id);

    /**
     * 修改数量
     * @param id
     * @param num
     * @return
     */
    int modifyNum(@Param("id") Integer id, @Param("num") int num);

    /**
     * 删除购物车
     * @param id
     * @return
     */
    int deleteCarTable(Integer id);

    int countCarTable(Integer userId);

    /**
     * 分页获取购物车
     * @param userId
     * @param page
     * @param size
     * @return
     */
    List<CarTableAndProduct> getCarTableAndProductList(@Param("userId") Integer userId,
                                                       @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 查询购物车
     * @param userId
     * @param mailProductId
     * @param productTypeId
     * @return
     */
    CarTable getCarTable(@Param("userId") Integer userId, @Param("mailProductId") Integer mailProductId,
                         @Param("productTypeId") Integer productTypeId);

}
