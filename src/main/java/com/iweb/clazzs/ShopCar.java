package com.iweb.clazzs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 小火煮粥
 * @date 2023/7/18 9:18
 * @WeChat xhzz211423
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopCar {
    private int shopCar_id;
    private int user_id;
    private int product_id;
    private double product_price;
    private int product_num;
    private double sum_price;


}
