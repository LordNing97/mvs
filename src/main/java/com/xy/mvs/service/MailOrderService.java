package com.xy.mvs.service;

import com.xy.mvs.api.Result;
import com.xy.mvs.api.ResultCode;
import com.xy.mvs.mapper.*;
import com.xy.mvs.model.*;
import com.xy.mvs.util.UUIDUtil;
import com.xy.mvs.vo.ConfirmCarTableVo;
import com.xy.mvs.vo.ConsignVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 陈璇
 * @Date 2021/1/12 11:18
 * @Version 1.0
 */
@Service
public class MailOrderService {

    @Resource
    private MailOrderMapper mailOrderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private MailProductMapper mailProductMapper;

    @Resource
    private ProductTypeMapper productTypeMapper;

    @Resource
    private CarTableMapper carTableMapper;

    /**
     * 保存订单
     * @param confirmCarTableVo
     * @return
     */
    @Transactional
    public Result saveOrder(ConfirmCarTableVo confirmCarTableVo){
        int result = 0;
        //新建订单
        MailOrder mailOrder = new MailOrder();
        mailOrder.setUserId(confirmCarTableVo.getUserId());
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
     * 下单
     * @param confirmCarTableVo
     */
    public void placeAnOrder(ConfirmCarTableVo confirmCarTableVo){
        // 将对象转为字符串 便于传输
        String json = JSONUtil.beanToJson(confirmCarTableVo);
        String msgId = UUIDUtil.randomID();
        /**
         * 构建Message ,主要是使用 msgId 将 message 和 CorrelationData 关联起来。
         * 这样当消息发送到交换机失败的时候，在 MsgSendConfirmCallBack 中就可以根据
         * correlationData.getId()即 msgId,知道具体是哪个message发送失败,进而进行处理。
         */
        // 将msgId和 message绑定
        Message message = MessageBuilder.withBody(json.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setCorrelationId(msgId)
                .build();
        // 将msgId和CorrelationData绑定
        CorrelationData correlationData = new CorrelationData(msgId);
        System.out.println(correlationData);
        // TODO 将 msgId 与 Message 的关系保存起来
        /**
         * 将 msgId 与 Message 的关系保存起来,例如放到缓存中.
         * 当 MsgSendReturnCallback回调时（消息从交换机到队列失败）,进行处理 {@code MsgSendReturnCallback}.
         * 当 MsgSendConfirmCallBack回调时,进行处理 {@code MsgSendConfirmCallBack}.
         * 定时检查这个绑定关系列表,如果发现一些已经超时(自己设定的超时时间)未被处理,则手动处理这些消息.
         */
        // 发送消息
        // 指定消息交换机  "car_table_exchange"
        // 指定队列key    "car_table_queue"
        rabbitTemplate.convertAndSend("car_table_exchange", "car_table_routing_key",
                message, correlationData);
    }

    /**
     * 付款
     * @param id
     * @return
     */
    @Transactional
    public boolean payment(String id, Double payment, Double cutMoney, Double freight){
        //获取订单
        Order order = orderMapper.getById(id);
        //获取订单详情
        List<OrderItem> orderItemList = orderItemMapper.getByOrderId(id);
        String productName = "";
        for(int i = 0;i < orderItemList.size();i++){
            //获取商品原库存
            ProductType productType = productTypeMapper.getById(orderItemList.get(i).getProductTypeId());
            int num = productType.getNum();
            productTypeMapper.modifyNum(productType.getId(), num - orderItemList.get(i).getNum());
            productName += orderItemList.get(i).getTitle() + ",";
        }
        //获取用户ID
        String userId = orderMapper.getById(id).getUserId();
        //获取用户
        User user = userMapper.getById(userId);
        //修改用户积分
        userMapper.modifyIntegral(userId, user.getIntegral() + 10);
        //修改累计节省
        userMapper.modifyCutMoney(userId, user.getCutMoney() + cutMoney);
        //推送通知
        if(productName.substring(productName.length() - 1).equals(",")){
            productName = productName.substring(0, productName.length() - 1);
        }
        PushUtil.sendPaymentMsg(user.getOpenid(), productName, order.getOrderNumber(), Double.toString(payment));
        if(order.getReceivingWay() == 1){
            return orderMapper.payment(id, LocalDateTime.now(), payment, freight, cutMoney, 5) > 0;
        }else{
            return orderMapper.payment(id, LocalDateTime.now(), payment, freight, cutMoney, 1) > 0;
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
        shippingInformation.setId(UUIDUtil.randomID());
        shippingInformation.setCreateTime(LocalDateTime.now());
        shippingInformation.setIsDel(0);
        shippingInformationMapper.saveShippingInformation(shippingInformation);
        //修改订单状态
        return mailOrderMapper.consign(consignVo.getOrderId(), LocalDateTime.now()) > 0;
    }

    /**
     * 交易完成
     * @param id
     * @return
     */
    public boolean end(String id){
        return mailOrderMapper.end(id, LocalDateTime.now()) > 0;
    }

    /**
     * 取消订单
     * @param id
     * @return
     */
    @Transactional
    public boolean cancelOrder(String id){
        orderItemMapper.cancelOrderItem(id);
        return orderMapper.cancelOrder(id) > 0;
    }

    /**
     * 根据用户ID和状态获取订单
     * @param userId
     * @param status
     * @return
     */
    public List<OrderAndItemInfoList> getByUserIdAndStatus(String userId, Integer status){
        List<OrderAndItemInfoList> orderAndItemInfoList = orderMapper.getByUserIdAndStatus(userId, status);
        for(int j = 0;j < orderAndItemInfoList.size();j++){
            //获取订单详细信息
            List<OrderItem> orderItem = orderItemMapper.getByOrderId(orderAndItemInfoList.get(j).getId());
            for(int i = 0;i < orderItem.size();i++){
                //获取产品
                Product product = productMapper.getById(orderItem.get(i).getProductId());
                orderItem.get(i).setIntroduction(product.getIntroduce());
            }
            orderAndItemInfoList.get(j).setOrderItem(orderItem);
        }
        for(int i = 0;i < orderAndItemInfoList.size();i++){
            if(orderAndItemInfoList.get(i).getStatus().equals(8)){
                OrderRefund orderRefund = orderRefundMapper.getByOrderId(orderAndItemInfoList.get(i).getId());
                orderAndItemInfoList.get(i).setOrderRefund(orderRefund);
            }
        }
        return orderAndItemInfoList;
    }

    /**
     * 根据ID获取订单详细信息
     * @param id
     * @return
     */
    @Transactional
    public OrderDetails getOrderDetails(String id){
        //获取订单信息
        Order order = orderMapper.getById(id);
        //获取订单详细信息
        List<OrderItem> orderItem = orderItemMapper.getByOrderId(id);
        for(int i = 0;i < orderItem.size();i++){
            //获取产品
            Product product = productMapper.getById(orderItem.get(i).getProductId());
            orderItem.get(i).setIntroduction(product.getIntroduce());
        }
        //获取收货地址
        Address address = addressMapper.getById(order.getAddressId());
        //获取运送信息
        ShippingInformation shippingInformation = shippingInformationMapper.getByOrderId(id);
        //获取退款订单
        OrderRefund orderRefund = orderRefundMapper.getByOrderId(id);
        //新建订单详细信息
        OrderDetails orderDetails = OrderDetails.builder()
                .order(order)
                .orderItem(orderItem)
                .shippingInformation(shippingInformation)
                .address(address)
                .orderRefund(orderRefund)
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
        List<OrderAndItemInfoList> orderList = orderMapper.getOrderByStatus(status, (page - 1) * size, size);
        for(int i = 0;i < orderList.size();i++){
            String orderId = orderList.get(i).getId();
            //获取订单详细信息
            List<OrderItem> orderItem = orderItemMapper.getByOrderId(orderId);
            for(int j = 0;j < orderItem.size();j++){
                //获取产品
                Product product = productMapper.getById(orderItem.get(j).getProductId());
                orderItem.get(j).setIntroduction(product.getIntroduce());
            }
            orderList.get(i).setOrderItem(orderItem);
            if(orderList.get(i).getStatus().equals(8)){
                OrderRefund orderRefund = orderRefundMapper.getByOrderId(orderList.get(i).getId());
                orderList.get(i).setOrderRefund(orderRefund);
            }
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
                .total(orderMapper.countOrderByStatus(status))
                .page(page)
                .build();
    }

    /**
     * 导出待发货订单
     * @return
     */
    public List<OrderExcel> listOrderExcel(){
        List<OrderExcel> orderExcelList = orderMapper.listOrderExcel();
//        List<OrderExcel> orderExcels = new ArrayList<>();
//        List<OrderExcel> orderExcels1 = new ArrayList<>();
//        for(int i = 0;i < orderExcelList.size();i++){
//            OrderExcel orderExcel = orderExcelList.get(i);
//            OrderExcel nextOrderExcel = orderExcelList.get(i + 1);
//            if(nextOrderExcel.getOrderNumber().equals(orderExcel.getOrderNumber())){
//                orderExcels.add(orderExcel);
//                orderExcels.add(nextOrderExcel);
//            }
//        }
        return orderExcelList;
    }

    /**
     * 自动完成订单
     * @return
     */
    public boolean autoEndOrder(LocalDateTime endTime){
        return orderMapper.autoEndOrder(endTime) > 0;
    }

}
