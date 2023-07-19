package com.iweb.DAO;

import com.iweb.clazzs.Admin;

import java.util.Collection;

/**
 * @author tang
 * @date 2023/7/17 18:52
 */
public interface AdminDAO {
    /**
     * 管理员注册
     *
     * @param admin 提供管理员信息
     */
    void adminRegister(Admin admin);

    /**
     * 显示所有用户信息 用于登录和注册判断
     *
     * @return 返回所有管理员集合
     */
    Collection<Admin> listAllAdmin();
}
