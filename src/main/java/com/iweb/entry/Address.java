package com.iweb.entry;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 小火煮粥
 * @date 2023/7/18 8:50
 * @WeChat xhzz211423
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private int user_id;
    private int address_id;
    private String detail;


}
