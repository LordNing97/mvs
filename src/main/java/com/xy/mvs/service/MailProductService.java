package com.xy.mvs.service;

import com.xy.mvs.mapper.MailProductMapper;
import com.xy.mvs.model.MailProduct;
import com.xy.mvs.request.MailProductList;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/9 13:59
 * @Version 1.0
 */
@Service
public class MailProductService {

    @Resource
    private MailProductMapper mailProductMapper;

    /**
     * 保存商城产品
     * @param mailProduct
     * @return
     */
    public boolean saveMailProduct(MailProduct mailProduct){
        mailProduct.setIsDel(0);
        return mailProductMapper.saveMailProduct(mailProduct) > 0;
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    public MailProduct getById(Integer id){
        return mailProductMapper.getById(id);
    }

    /**
     * 修改商城产品
     * @param mailProduct
     * @return
     */
    public boolean modifyMailProduct(MailProduct mailProduct){
        return mailProductMapper.modifyMailProduct(mailProduct) > 0;
    }

    /**
     * 分页获取商城产品
     * @param name
     * @param productTypeId
     * @param status
     * @param page
     * @param size
     * @return
     */
    public MailProductList getMailProductList(String name, Integer productTypeId, Integer status, Integer page, Integer size){
        List<MailProduct> mailProductList = mailProductMapper.getMailProductList(name, productTypeId, status, (page - 1) * size, size);
        return MailProductList.builder()
                .mailProductList(mailProductList)
                .total(mailProductMapper.countMailProduct(name, productTypeId, status))
                .page(page)
                .build();
    }

    /**
     * 删除商城产品
     * @param id
     * @return
     */
    public boolean deleteMailProduct(Integer id){
        return mailProductMapper.deleteMailProduct(id) > 0;
    }

}
