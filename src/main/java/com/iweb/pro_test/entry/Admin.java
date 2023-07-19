package com.iweb.pro_test.entry;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 小火煮粥
 * @date 2023/7/18 8:34
 * @WeChat xhzz211423
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private int admin_id;
    private String admin_name;
    private String admin_password;
    private int admin_phone;

    public Admin(String admin_name, String admin_password, int admin_phone) {
        this.admin_name = admin_name;
        this.admin_password = admin_password;
        this.admin_phone = admin_phone;
    }

    public Admin(String admin_name, String admin_password) {
        this.admin_name = admin_name;
        this.admin_password = admin_password;
    }
}
