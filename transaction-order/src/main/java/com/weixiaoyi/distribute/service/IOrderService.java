package com.weixiaoyi.distribute.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weixiaoyi.distribute.entity.MyOrder;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuanLong Wei
 * @since 2019-04-30
 */
public interface IOrderService extends IService<MyOrder> {

    /**
     * 声明式事务
     * 远程调用发货接口进行发货处理 并修改订单状态
     * 方法级别的事务管理，占用链接池资源，连接池数量使用达到上限后会影响其他操作
     *
     * @param orderId
     * @return
     */
    boolean handOut(String orderId);

    /**
     * 编程式事务
     * 细粒度事务管理控制，使用链接后会立即释放，再执行其他业务操作
     * 不会防止恶意攻击
     *
     * @param orderId
     * @return
     */
    boolean handOut2(String orderId);

    /**
     * 编程式事务
     * 细粒度事务管理控制，使用链接后会立即释放，再执行其他业务操作
     * 不会防止恶意攻击
     *
     * @param orderId
     * @return
     */
    boolean handOut3(String orderId);

}
