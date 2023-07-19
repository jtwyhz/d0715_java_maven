package com.iweb.clazzs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tang
 * @date 2023/7/18 15:55
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Address {
    private int user_id;
    private int address_id;
    private String detail;

}
