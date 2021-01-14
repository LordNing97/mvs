package com.xy.mvs.service;

import com.xy.mvs.mapper.CarTableMapper;
import com.xy.mvs.model.CarTable;
import com.xy.mvs.request.CarTableAndProduct;
import com.xy.mvs.request.CarTableAndProductList;
import com.xy.mvs.vo.CarTableVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/14 11:29
 * @Version 1.0
 */
@Service
public class CarTableService {

    @Resource
    private CarTableMapper carTableMapper;

    /**
     * 保存购物车
     * @param carTableVo
     * @returnng
     */
    public boolean saveCarTable(CarTableVo carTableVo){
        CarTable carTable = carTableMapper.getCarTable(carTableVo.getUserId(), carTableVo.getMailProductId(), carTableVo.getProductTypeId());
        if(carTable != null){
            Integer id = carTable.getId();
            int num = carTableMapper.getById(id).getNum();
            return carTableMapper.modifyNum(id, num + 1) > 0;
        }else{
            carTable = new CarTable();
            BeanUtils.copyProperties(carTableVo, carTable);
            carTable.setCreateTime(LocalDateTime.now());
            carTable.setIsDel(0);
            return carTableMapper.saveCarTable(carTable) > 0;
        }
    }

    /**
     * 添加数量
     * @param id
     * @return
     */
    @Transactional
    public boolean addNum(Integer id){
        int num = carTableMapper.getById(id).getNum();
        return carTableMapper.modifyNum(id, num + 1) > 0;
    }

    /**
     * 减少数量
     * @param id
     * @return
     */
    @Transactional
    public boolean cutNum(Integer id){
        int num = carTableMapper.getById(id).getNum();
        return carTableMapper.modifyNum(id, num - 1) > 0;
    }

    /**
     * 删除购物车
     * @param id
     * @return
     */
    public boolean deleteCarTable(Integer id){
        return carTableMapper.deleteCarTable(id) > 0;
    }

    /**
     * 分页获取购物车
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public CarTableAndProductList getCarTableAndProductList(Integer userId, Integer page, Integer size){
        List<CarTableAndProduct> carTableAndProductList = carTableMapper.getCarTableAndProductList(userId, (page - 1) * size, size);
        return CarTableAndProductList.builder()
                .carTableAndProductList(carTableAndProductList)
                .total(carTableMapper.countCarTable(userId))
                .page(page)
                .build();
    }

}
