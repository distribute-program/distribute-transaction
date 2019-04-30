package com.weixiaoyi.distribute;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.weixiaoyi.distribute.mapper")
public class TransactionOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionOrderApplication.class, args);
    }

}
