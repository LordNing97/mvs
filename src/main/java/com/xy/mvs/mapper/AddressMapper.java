package com.xy.mvs.mapper;

import com.xy.mvs.model.Address;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/15 14:50
 * @Version 1.0
 */
@Mapper
public interface AddressMapper {

    /**
     * 保存地址
     * @param address
     * @return
     */
    int saveAddress(Address address);

    /**
     * 根据ID获取地址
     * @param id
     * @return
     */
    Address getById(Integer id);

    /**
     * 获取用户地址
     * @param userId
     * @return
     */
    List<Address> getAddressList(Integer userId);

    /**
     * 修改地址
     * @param address
     * @return
     */
    int modifyAddress(Address address);

    /**
     * 删除地址
     * @param id
     * @return
     */
    int deleteAddress(Integer id);

}
