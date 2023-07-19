package com.iweb.pro_test.entry;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 小火煮粥
 * @date 2023/7/18 9:06
 * @WeChat xhzz211423
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private int order_num;
    private double order_sum_price;
    private String order_state;
}
