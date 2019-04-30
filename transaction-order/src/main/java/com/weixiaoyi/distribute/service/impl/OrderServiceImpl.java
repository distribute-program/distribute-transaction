package com.weixiaoyi.distribute.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixiaoyi.distribute.entity.MyOrder;
import com.weixiaoyi.distribute.mapper.OrderMapper;
import com.weixiaoyi.distribute.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuanLong Wei
 * @since 2019-04-30
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, MyOrder> implements IOrderService {

    /** 调用远程http接口 */
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Transactional
    @Override
    public boolean handOut(String orderId) {
        log.info("开始发货");
        String status = httpHandOut(orderId);
        log.info("返回的数据为：{}",status);
        return updateOrderStatus(orderId,Integer.valueOf(status));
    }

    @Override
    public boolean handOut2(String orderId) {
        // 修改状态为 发货中
        transactionTemplate.execute(a -> {
            updateOrderStatus(orderId, 1);
            return null;
        });

        log.info("开始发货");
        String status = httpHandOut(orderId);

        // 发货完成后 将订单状态修改为收到的结果
        transactionTemplate.execute(a -> {
            updateOrderStatus(orderId, Integer.valueOf(status));
            return null;
        });
        log.info("修改订单状态完成：{}",status);
        return true;
    }

    /**
     * 利用乐观锁进行锁操作
     * 发过货就将订单版本为0的订单 修改状态为1  修改版本为0  返回是否修改成功
     * 如果已经发过货的订单 返回结果为false 就不将进行发货处理
     *
     * @param orderId
     * @return
     */
    @Override
    public boolean handOut3(String orderId) {
        Boolean execute = transactionTemplate.execute(a -> {
            return updateOrderStatus2(orderId, 1);
        });

        if(execute) {
            log.info("开始发货");
            String status = httpHandOut(orderId);
            transactionTemplate.execute(a -> {
                updateOrderStatus(orderId, Integer.valueOf(status));
                return null;
            });
            log.info("修改订单状态完成：{}",status);
        }else {
            log.info("发货失败----------------");
        }
        return true;
    }

    /**
     * 调用transaction-handout项目 进行另一个项目的业务处理
     *
     * @param orderId
     * @return
     */
    private String httpHandOut(String orderId) {
        String getUrl = "http://localhost:8002/hand/handOut?orderId=" + orderId;
        ResponseEntity<String> forEntity = restTemplate.getForEntity(getUrl, String.class);
        return forEntity.getBody();
    }

    /**
     *
     *
     * @param orderId
     * @return
     */
    private boolean updateOrderStatus(String orderId, Integer status) {
        MyOrder order = new MyOrder();
        order.setStatus(status);
        QueryWrapper<MyOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",orderId);
        return this.update(order, queryWrapper);
    }

    private boolean updateOrderStatus2(String orderId, int status) {
        MyOrder order = new MyOrder();
        order.setStatus(status);
        order.setVersion(1);
        QueryWrapper<MyOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",orderId);
        queryWrapper.eq("version",0);
        return this.update(order, queryWrapper);
    }

}
