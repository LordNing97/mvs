package com.xy.mvs.mapper;

import com.xy.mvs.model.LotteryProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/10 17:17
 * @Version 1.0
 **/
@Mapper
public interface LotteryProductMapper {

    /**
     * 保存抽奖产品
     * @param lotteryProduct
     * @return
     */
    int saveLotteryProduct(LotteryProduct lotteryProduct);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    LotteryProduct getById(Integer id);

    /**
     * 修改抽奖产品
     * @param lotteryProduct
     * @return
     */
    int modifyLotteryProduct(LotteryProduct lotteryProduct);

    int countLotteryProduct(@Param("name") String name, @Param("productTypeId") Integer productTypeId, @Param("status") Integer status);

    /**
     * 分页获取抽奖产品
     * @param name
     * @param productTypeId
     * @param status
     * @param page
     * @param size
     * @return
     */
    List<LotteryProduct> getLotteryProductList(@Param("name") String name, @Param("productTypeId") Integer productTypeId, @Param("status") Integer status,
                                         @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 删除抽奖产品
     * @param id
     * @return
     */
    int deleteLotteryProduct(Integer id);

}
