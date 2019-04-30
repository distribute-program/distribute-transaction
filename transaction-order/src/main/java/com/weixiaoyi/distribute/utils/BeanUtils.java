package com.weixiaoyi.distribute.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/4/30 13:20
 * @description：实例化对象工具类
 * @modified By：
 * @version: 1.0
 */
@Component
public class BeanUtils {

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

}
