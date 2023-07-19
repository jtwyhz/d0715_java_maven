package com.iweb.pro_test.entry;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 小火煮粥
 * @date 2023/7/18 9:02
 * @WeChat xhzz211423
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Property {
    private int property_id;
    private String property_name;
    private String property_describe;
}
