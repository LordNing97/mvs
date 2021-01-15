package com.xy.mvs.service;

import com.xy.mvs.mapper.AddressMapper;
import com.xy.mvs.model.Address;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/15 14:51
 * @Version 1.0
 */
@Service
public class AddressService {

    @Resource
    private AddressMapper addressMapper;

    /**
     * 保存地址
     * @param address
     * @return
     */
    public boolean saveAddress(Address address){
        address.setIsDel(0);
        return addressMapper.saveAddress(address) > 0;
    }

    /**
     * 根据ID获取地址
     * @param id
     * @return
     */
    public Address getById(Integer id){
        return addressMapper.getById(id);
    }

    /**
     * 获取用户地址
     * @param userId
     * @return
     */
    public List<Address> getAddressList(Integer userId){
        return addressMapper.getAddressList(userId);
    }

    /**
     * 编辑地址
     * @param address
     * @return
     */
    public boolean modifyAddress(Address address){
        return addressMapper.modifyAddress(address) > 0;
    }

    /**
     * 删除地址
     * @param id
     * @return
     */
    public boolean deleteAddress(Integer id){
        return addressMapper.deleteAddress(id) > 0;
    }

}
