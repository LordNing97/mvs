package com.xy.mvs.service;

import com.xy.mvs.mapper.LotteryProductMapper;
import com.xy.mvs.model.LotteryProduct;
import com.xy.mvs.request.LotteryProductList;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/10 17:18
 * @Version 1.0
 **/
@Service
public class LotteryProductService {

    @Resource
    private LotteryProductMapper lotteryProductMapper;

    /**
     * 保存抽奖产品
     * @param lotteryProduct
     * @return
     */
    public boolean saveLotteryProduct(LotteryProduct lotteryProduct){
        lotteryProduct.setIsDel(0);
        return lotteryProductMapper.saveLotteryProduct(lotteryProduct) > 0;
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    public LotteryProduct getById(Integer id){
        return lotteryProductMapper.getById(id);
    }

    /**
     * 修改抽奖产品
     * @param lotteryProduct
     * @return
     */
    public boolean modifyLotteryProduct(LotteryProduct lotteryProduct){
        return lotteryProductMapper.modifyLotteryProduct(lotteryProduct) > 0;
    }

    /**
     * 分页获取抽奖产品
     * @param name
     * @param productTypeId
     * @param status
     * @param page
     * @param size
     * @return
     */
    public LotteryProductList getLotteryProductList(String name, Integer productTypeId, Integer status, Integer page, Integer size){
        List<LotteryProduct> lotteryProductList = lotteryProductMapper.getLotteryProductList(name, productTypeId, status, (page - 1) * size, size);
        return LotteryProductList.builder()
                .lotteryProductList(lotteryProductList)
                .total(lotteryProductMapper.countLotteryProduct(name, productTypeId, status))
                .page(page)
                .build();
    }

    /**
     * 删除抽奖产品
     * @param id
     * @return
     */
    public boolean deleteLotteryProduct(Integer id){
        return lotteryProductMapper.deleteLotteryProduct(id) > 0;
    }

}
