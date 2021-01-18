package com.xy.mvs.service;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.mapper.*;
import com.xy.mvs.model.Address;
import com.xy.mvs.model.OrderItem;
import com.xy.mvs.model.ProductType;
import com.xy.mvs.model.ShippingInformation;
import com.xy.mvs.request.OrderAndItemInfoList;
import com.xy.mvs.request.OrderDetails;
import com.xy.mvs.request.OrderExcel;
import com.xy.mvs.request.OrderList;
import com.xy.mvs.util.UUIDUtil;
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

    /**
     * 保存订单
     * @param confirmCarTableVo
     * @return
     */
    @Transactional
    public Result saveMailOrder(ConfirmCarTableVo confirmCarTableVo){
        int result = 0;
        //新建订单
        MailOrder mailOrder = new MailOrder();
        mailOrder.setCustomerId(confirmCarTableVo.getUserId());
        mailOrder.setAddressId(confirmCarTableVo.getAddressId());
        mailOrder.setTotalPrice(confirmCarTableVo.getTotalPrice());
        mailOrder.setPayment(confirmCarTableVo.getPayment());
        mailOrder.setOrderNumber(UUIDUtil.randomID());
        mailOrder.setTotalNum(confirmCarTableVo.getTotalNum());
        mailOrder.setMemberCut(confirmCarTableVo.getMemberCut());
        mailOrder.setStatus(0);
        mailOrder.setCreateTime(LocalDateTime.now());
        mailOrder.setIsDel(0);
        if(!StringUtils.isEmpty(confirmCarTableVo.getMailProductId())){
            //新建订单详情
            //根据ID获取产品
            MailProduct mailProduct = mailProductMapper.getById(confirmCarTableVo.getMailProductId());
            ProductType productType = productTypeMapper.getById(confirmCarTableVo.getProductTypeId());
            if(mailProduct.getNum() < confirmCarTableVo.getNum()){
                return Result.builder(101, "库存不足").build();
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(mailOrder.getId());
            orderItem.setMailProductId(mailProduct.getId());
            orderItem.setProductTypeId(productType.getId());
            orderItem.setProductTypeName(productType.getName());
            orderItem.setNum(confirmCarTableVo.getNum());
            orderItem.setTitle(mailProduct.getName());
            orderItem.setPrice(mailProduct.getPrice());
            orderItem.setTotalPrice(confirmCarTableVo.getNum() * mailProduct.getPrice());
            orderItem.setPhoto(mailProduct.getPhoto());
            orderItem.setCreateTime(LocalDateTime.now());
            orderItem.setIsDel(0);
            result = orderItemMapper.saveOrderItem(orderItem);
        }else{
            String carTableIdsStr = confirmCarTableVo.getCarTableIds();
            String[] carTableIds = carTableIdsStr.split(",");
            for(int i = 0;i < carTableIds.length;i++){
                //根据ID获取购物车
                CarTable carTable = carTableMapper.getById(Integer.parseInt(carTableIds[i]));
                //根据产品ID获取产品
                MailProduct mailProduct = mailProductMapper.getById(carTable.getMailProductId());
                ProductType productType = productTypeMapper.getById(carTable.getProductTypeId());
                if(mailProduct.getNum() < carTable.getNum()){
                    return Result.builder(101, "库存不足").build();
                }
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(mailOrder.getId());
                orderItem.setMailProductId(mailProduct.getId());
                orderItem.setProductTypeId(productType.getId());
                orderItem.setProductTypeName(productType.getName());
                orderItem.setNum(carTable.getNum());
                orderItem.setTitle(mailProduct.getName());
                orderItem.setPrice(mailProduct.getPrice());
                orderItem.setTotalPrice(carTable.getNum() * mailProduct.getPrice());
                orderItem.setPhoto(mailProduct.getPhoto());
                orderItem.setCreateTime(LocalDateTime.now());
                orderItem.setIsDel(0);
                result = orderItemMapper.saveOrderItem(orderItem);
                //删除购物车
                carTableMapper.deleteCarTable(Integer.parseInt(carTableIds[i]));
            }
        }
        //保存订单
        mailOrderMapper.saveMailOrder(mailOrder);
        if(result > 0){
            return Result.builder().data(mailOrder.getId()).build();
        }else{
            return Result.builder(ResultCode.OPERATION_ERROR).build();
        }
    }

    /**
     * 付款
     * @param id
     * @return
     */
    @Transactional
    public boolean payment(Integer id, Double payment, Double cutMoney, Double freight){
        //获取订单
        MailOrder mailOrder = mailOrderMapper.getById(id);
        //获取订单详情
        List<OrderItem> orderItemList = orderItemMapper.getByOrderId(id);
        String productName = "";
        for(int i = 0;i < orderItemList.size();i++){
            //获取商品原库存
            MailProduct mailProduct = mailProductMapper.getById(orderItemList.get(i).getMailProductId());
            int num = mailProduct.getNum();
            mailProductMapper.modifyNum(mailProduct.getId(), num - orderItemList.get(i).getNum());
            productName += orderItemList.get(i).getTitle() + ",";
        }
        return mailOrderMapper.payment(id, LocalDateTime.now(), payment, freight, cutMoney, 1) > 0;
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
        return mailOrderMapper.end(id, LocalDateTime.now()) > 0;
    }

    /**
     * 根据用户ID和状态获取订单
     * @param customerId
     * @param status
     * @return
     */
    public List<OrderAndItemInfoList> getByUserIdAndStatus(Integer customerId, Integer status){
        List<OrderAndItemInfoList> orderAndItemInfoList = mailOrderMapper.getByUserIdAndStatus(customerId, status);
        for(int j = 0;j < orderAndItemInfoList.size();j++){
            //获取订单详细信息
            List<OrderItem> orderItem = orderItemMapper.getByOrderId(orderAndItemInfoList.get(j).getId());
            for(int i = 0;i < orderItem.size();i++){
                //获取产品
                MailProduct mailProduct = mailProductMapper.getById(orderItem.get(i).getMailProductId());
                orderItem.get(i).setProductTypeName(mailProduct.getName());
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
        MailOrder mailOrder = mailOrderMapper.getById(id);
        //获取订单详细信息
        List<OrderItem> orderItem = orderItemMapper.getByOrderId(id);
        for(int i = 0;i < orderItem.size();i++){
            //获取产品
            MailProduct mailProduct = mailProductMapper.getById(orderItem.get(i).getMailProductId());
            orderItem.get(i).setProductTypeName(mailProduct.getName());
        }
        //获取收货地址
        Address address = addressMapper.getById(mailOrder.getAddressId());
        //获取运送信息
        ShippingInformation shippingInformation = shippingInformationMapper.getByOrderId(id);
        //新建订单详细信息
        OrderDetails orderDetails = OrderDetails.builder()
                .mailOrder(mailOrder)
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
                MailProduct mailProduct = mailProductMapper.getById(orderItem.get(j).getMailProductId());
                orderItem.get(j).setProductTypeName(mailProduct.getName());
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
