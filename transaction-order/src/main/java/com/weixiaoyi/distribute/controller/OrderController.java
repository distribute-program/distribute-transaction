package com.weixiaoyi.distribute.controller;

import com.alibaba.fastjson.JSON;
import com.weixiaoyi.distribute.entity.MyOrder;
import com.weixiaoyi.distribute.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/4/30 13:13
 * @description：订单处理控制器
 * @modified By：
 * @version: 1.0
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private IOrderService orderService;

    @RequestMapping("handOut")
    public String handOut(String orderId) {
        log.info("参数为：{}",orderId);
        boolean status = orderService.handOut(orderId);
        return status + "";
    }

    @RequestMapping("handOut2")
    public String handOut2(String orderId) {
        log.info("参数为：{}",orderId);
        boolean status = orderService.handOut2(orderId);
        return status + "";
    }

    @RequestMapping("handOut3")
    public String handOut3(String orderId) {
        log.info("参数为：{}",orderId);

        boolean status = false;
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                     orderService.handOut3(orderId);
                }
            }).start();
        }
        return status + "";
    }

    @RequestMapping("queryOrderById")
    public String queryOrderById(Integer id) {
        MyOrder order = orderService.getById(id);
        log.info("查询到订单：{}",order);
        return JSON.toJSONString(order);
    }

}
