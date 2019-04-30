package com.weixiaoyi.distribute.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/4/30 13:22
 * @description：派发处理控制器
 * @modified By：
 * @version: 1.0
 */
@RestController
@Slf4j
public class HandOutController {

    @RequestMapping("handOut")
    public String handOut(String orderId) {
        log.info("收到订单号为：{}",orderId);

        try {
            // 模仿业务处理  持续十秒 返回1 标识处理成功
            Thread.sleep(10000);
            return "2";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "3";
    }

}
