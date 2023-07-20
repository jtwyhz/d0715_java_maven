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

    @Override
    public String toString() {
        return "商品{" +
                "id=" + product_id +
                product_name +
                ", 价格：" + product_price +
                ", 库存：" + stock_num +
                ", 销量：" + sales_num +
                ", " + property_name  +
                ", 描述：" + property_describe +
                '}'+"\n";
    }
}
