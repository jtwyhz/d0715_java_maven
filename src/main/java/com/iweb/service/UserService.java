package com.iweb.service;


import com.iweb.DAO.UserDAO;
import com.iweb.DAO.impl.UserDAOImpl;
import com.iweb.clazzs.User;
import com.iweb.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author 小火煮粥
 * @date 2023/7/18 14:29
 * @WeChat xhzz211423
 */


public class UserService {

    //        定义实现类
    static UserDAO userDAO = new UserDAOImpl();
    //        集合传参
    static ArrayList<User> cu = (ArrayList) userDAO.listAllUser();

    //    判断登录用户是否存在
    public static boolean isUser(User user) {
        //        定义bollean变量
        boolean isUser = false;
//        判断登录用户是否存在 账号密码是否正确
        loop:
        for (User u : cu) {
//            比较用户名和密码
            if (u.getUser_name().equals(user.getUser_name()) && u.getUser_password().equals(user.getUser_password())) {
//                    用户名密码正确登录成功
                isUser = true;
                System.out.println("******登录成功******");
                View.currentUser=u;
                break loop;
            }
//            else {
//                System.out.println("用户名或密码错误,重新输入");
//                View.userLoginView();
//            }
        }
        return isUser;
    }

    //    判断用户是否注册
    public static boolean isRegister(User user) {
        UserDAO userDAO = new UserDAOImpl();
        boolean isRegister = true;
        loop:
        for (User u : cu) {
//            判断用户名是否存在
            if (u.getUser_name().equals(user.getUser_name())) {
                System.out.println("用户名存在 请重写输入");
                isRegister = false;
                // 返回注册界面
                View.userRegisterView();
                break loop;
            }
        }
        System.out.println("注册成功");
        return isRegister;
    }


}
