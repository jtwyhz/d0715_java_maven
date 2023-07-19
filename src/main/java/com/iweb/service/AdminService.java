package com.iweb.service;


import com.iweb.DAO.AdminDAO;
import com.iweb.DAO.impl.AdminDAOImpl;
import com.iweb.clazzs.Admin;
import com.iweb.view.View;

import java.util.Collection;

/**
 * @Author 小火煮粥
 * @date 2023/7/18 18:23
 * @WeChat xhzz211423
 */


public class AdminService {
    //        定义实现类
    static AdminDAO adminDAO =  new AdminDAOImpl();
    //        集合传参
//    static Collection<Admin> ca = adminDAO.listAllAdmin();
static Collection<Admin> ca=adminDAO.listAllAdmin();
    //    判断登录用户是否存在
    public static boolean isAdmin(Admin admin) {
//        定义bollean变量
        boolean isAdmin = false;
//        判断登录用户是否存在 账号密码是否正确
        loop:
        for (Admin a : ca) {
//            比较用户名和密码
            if (a.getAdmin_name().equals(admin.getAdmin_name()) && a.getAdmin_password().equals(admin.getAdmin_password())) {
//                    用户名密码正确登录成功
                isAdmin = true;
                System.out.println("登录成功");
                break loop;
            } else {
                System.out.println("用户名或密码错误,重新输入");
                View.adminLoginView();
            }
        }
        return isAdmin;
    }

    //    判断管理员是否注册
    public static boolean isRegister(Admin admin) {
        boolean isRegister = true;
        loop:
        for (Admin a : ca) {
//            判断用户名是否存在
            if (a.getAdmin_name().equals(admin.getAdmin_name())) {
                System.out.println("管理员存在 请重写输入");
                isRegister = false;
//                返回注册界面
                View.userRegisterView();
            }
        }
        System.out.println("注册成功");
        return isRegister;
    }
}
