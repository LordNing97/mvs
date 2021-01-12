package com.xy.mvs.service;

import com.xy.mvs.mapper.MailOrderMapper;
import com.xy.mvs.model.MailOrder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Author 陈璇
 * @Date 2021/1/12 11:18
 * @Version 1.0
 */
@Service
public class MailOrderService {

    @Resource
    private MailOrderMapper mailOrderMapper;

    /**
     * 保存商城订单
     * @param mailOrder
     * @return
     */
    public boolean saveMailOrder(MailOrder mailOrder){
        mailOrder.setCreateTime(LocalDateTime.now());
        mailOrder.setIsDel(0);
        return mailOrderMapper.saveMailOrder(mailOrder) > 0;
    }

}
