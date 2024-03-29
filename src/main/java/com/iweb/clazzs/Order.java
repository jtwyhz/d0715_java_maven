package com.iweb.clazzs;


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
    private int product_id;
    private String product_name;
    private double product_price;
    private int product_num;
    private double order_sum_price;
    private String order_state;

    @Override
    public String toString() {
        return "product_id"+product_id+",product_name"+product_name+",product_num"+product_num
                +",order_sum_price"+order_sum_price+",order_state"+order_state;
    }
}
