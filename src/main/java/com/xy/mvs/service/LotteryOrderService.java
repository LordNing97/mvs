package com.xy.mvs.service;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.mapper.*;
import com.xy.mvs.model.*;
import com.xy.mvs.request.OrderAndItemInfoList;
import com.xy.mvs.request.OrderDetails;
import com.xy.mvs.request.OrderExcel;
import com.xy.mvs.request.OrderList;
import com.xy.mvs.util.UUIDUtil;
import com.xy.mvs.vo.ConfirmCarTableVo;
import com.xy.mvs.vo.ConsignVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/18 15:18
 * @Version 1.0
 */
@Service
public class LotteryOrderService {

    @Resource
    private LotteryOrderMapper lotteryOrderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private ProductTypeMapper productTypeMapper;

    @Resource
    private ShippingInformationMapper shippingInformationMapper;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private LotteryProductMapper lotteryProductMapper;

    /**
     * 保存订单
     * @param confirmCarTableVo
     * @return
     */
    @Transactional
    public Result saveLotteryOrder(ConfirmCarTableVo confirmCarTableVo){
        int result = 0;
        //新建订单
        LotteryOrder lotteryOrder = new LotteryOrder();
        lotteryOrder.setCustomerId(confirmCarTableVo.getUserId());
        lotteryOrder.setAddressId(confirmCarTableVo.getAddressId());
        lotteryOrder.setOrderNumber(UUIDUtil.randomID());
        lotteryOrder.setStatus(0);
        lotteryOrder.setCreateTime(LocalDateTime.now());
        lotteryOrder.setIsDel(0);
        //新建订单详情
        //根据ID获取产品
        LotteryProduct lotteryProduct = lotteryProductMapper.getById(confirmCarTableVo.getMailProductId());
        ProductType productType = productTypeMapper.getById(confirmCarTableVo.getProductTypeId());
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(lotteryOrder.getId());
        orderItem.setMailProductId(lotteryProduct.getId());
        orderItem.setProductTypeId(productType.getId());
        orderItem.setProductTypeName(productType.getName());
        orderItem.setTitle(lotteryProduct.getName());
        orderItem.setPrice(lotteryProduct.getPrice());
        orderItem.setTotalPrice(lotteryProduct.getPrice());
        orderItem.setPhoto(lotteryProduct.getPhoto());
        orderItem.setCreateTime(LocalDateTime.now());
        orderItem.setIsDel(0);
        result = orderItemMapper.saveOrderItem(orderItem);
        //保存订单
        lotteryOrderMapper.saveLotteryOrder(lotteryOrder);
        if(result > 0){
            return Result.builder().data(lotteryOrder.getId()).build();
        }else{
            return Result.builder(ResultCode.OPERATION_ERROR).build();
        }
    }

    /**
     * 发货
     * @param consignVo
     * @return
     */
    @Transactional
    public boolean consign(ConsignVo consignVo){
        //填写运送信息
        ShippingInformation shippingInformation = new ShippingInformation();
        BeanUtils.copyProperties(consignVo, shippingInformation);
        shippingInformation.setCreateTime(LocalDateTime.now());
        shippingInformation.setIsDel(0);
        shippingInformationMapper.saveShippingInformation(shippingInformation);
        //修改订单状态
        return lotteryOrderMapper.consign(consignVo.getOrderId(), LocalDateTime.now()) > 0;
    }

    /**
     * 交易完成
     * @param id
     * @return
     */
    public boolean end(Integer id){
        return lotteryOrderMapper.end(id, LocalDateTime.now()) > 0;
    }

    /**
     * 根据用户ID和状态获取订单
     * @param customerId
     * @param status
     * @return
     */
    public List<OrderAndItemInfoList> getByUserIdAndStatus(Integer customerId, Integer status){
        List<OrderAndItemInfoList> orderAndItemInfoList = lotteryOrderMapper.getByUserIdAndStatus(customerId, status);
        for(int j = 0;j < orderAndItemInfoList.size();j++){
            //获取订单详细信息
            List<OrderItem> orderItem = orderItemMapper.getByOrderId(orderAndItemInfoList.get(j).getId());
            for(int i = 0;i < orderItem.size();i++){
                //获取产品
                LotteryProduct lotteryProduct = lotteryProductMapper.getById(orderItem.get(i).getMailProductId());
                orderItem.get(i).setProductTypeName(lotteryProduct.getName());
            }
            orderAndItemInfoList.get(j).setOrderItem(orderItem);
        }
        return orderAndItemInfoList;
    }

    /**
     * 根据ID获取订单详细信息
     * @param id
     * @return
     */
    @Transactional
    public OrderDetails getOrderDetails(Integer id){
        //获取订单信息
        LotteryOrder lotteryOrder = lotteryOrderMapper.getById(id);
        //获取订单详细信息
        List<OrderItem> orderItem = orderItemMapper.getByOrderId(id);
        for(int i = 0;i < orderItem.size();i++){
            //获取产品
            LotteryProduct lotteryProduct = lotteryProductMapper.getById(orderItem.get(i).getMailProductId());
            orderItem.get(i).setProductTypeName(lotteryProduct.getName());
        }
        //获取收货地址
        Address address = addressMapper.getById(lotteryOrder.getAddressId());
        //获取运送信息
        ShippingInformation shippingInformation = shippingInformationMapper.getByOrderId(id);
        //新建订单详细信息
        OrderDetails orderDetails = OrderDetails.builder()
                .lotteryOrder(lotteryOrder)
                .orderItem(orderItem)
                .shippingInformation(shippingInformation)
                .address(address)
                .build();
        return orderDetails;
    }

    /**
     * 分页获取订单
     * @param status
     * @param page
     * @param size
     * @return
     */
    public OrderList getOrderByStatus(Integer status, Integer page, Integer size){
        List<OrderAndItemInfoList> orderList = lotteryOrderMapper.getOrderByStatus(status, (page - 1) * size, size);
        for(int i = 0;i < orderList.size();i++){
            Integer orderId = orderList.get(i).getId();
            //获取订单详细信息
            List<OrderItem> orderItem = orderItemMapper.getByOrderId(orderId);
            for(int j = 0;j < orderItem.size();j++){
                //获取产品
                LotteryProduct lotteryProduct = lotteryProductMapper.getById(orderItem.get(i).getMailProductId());
                orderItem.get(j).setProductTypeName(lotteryProduct.getName());
            }
            orderList.get(i).setOrderItem(orderItem);
            //获取收货地址
            if(orderList.get(i).getAddressId() != null){
                Address address = addressMapper.getById(orderList.get(i).getAddressId());
                orderList.get(i).setAddress(address);
            }
            //获取运送信息
            ShippingInformation shippingInformation = shippingInformationMapper.getByOrderId(orderId);
            orderList.get(i).setShippingInformation(shippingInformation);
        }
        return OrderList.builder()
                .orderList(orderList)
                .total(lotteryOrderMapper.countOrderByStatus(status))
                .page(page)
                .build();
    }

    /**
     * 导出待发货订单
     * @return
     */
    public List<OrderExcel> listOrderExcel(){
        List<OrderExcel> orderExcelList = lotteryOrderMapper.listOrderExcel();
        return orderExcelList;
    }

    /**
     * 自动完成订单
     * @return
     */
    public boolean autoEndOrder(LocalDateTime endTime){
        return lotteryOrderMapper.autoEndOrder(endTime) > 0;
    }

}
