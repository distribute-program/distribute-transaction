package com.weixiaoyi.distribute.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuanLong Wei
 * @since 2019-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class MyOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String orderId;

    private Integer status;

    private Integer version;

}
