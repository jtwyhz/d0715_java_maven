package com.iweb.clazzs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 小火煮粥
 * @date 2023/7/18 8:22
 * @WeChat xhzz211423
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int user_id;
    private String user_name;
    private String user_password;
    private String user_address;
    private String user_phone;
    private double money;

    public User(String user_name, String user_password, String user_address, String user_phone, double money) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_address = user_address;
        this.user_phone = user_phone;
        this.money = money;
    }

    public User(int user_id,String user_name, String user_password, String user_phone, double money) {
        this.user_id=user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_phone = user_phone;
        this.money = money;
    }

    public User(String user_name, String user_password, String user_address, String user_phone) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_address = user_address;
        this.user_phone = user_phone;
    }

    public User(String user_name, String user_password, String user_phone) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_phone = user_phone;
    }
}
