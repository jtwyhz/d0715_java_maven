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
    private int product_num;
    private String sum_price;

}
