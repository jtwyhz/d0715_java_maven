package com.iweb.clazzs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 小火煮粥
 * @date 2023/7/18 8:57
 * @WeChat xhzz211423
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int product_id;
    private String product_name;
    private double product_price;
    private int stock_num;
    private int sales_num;
    private int property_id;
    private String property_name;
    private String property_describe;
    private int admin_id;

    public Product(String str, double v, int i, int i1, int i2, int i3) {
    }
}
