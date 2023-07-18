package com.iweb.pro_test.view;


import com.iweb.pro_test.DAO.UserDao;
import com.iweb.pro_test.DAO.UserDaoImpl;
import com.iweb.pro_test.clazzs.User;

import java.util.Scanner;

/**
 * @Author 小火煮粥
 * @date 2023/7/17 22:10
 * @WeChat xhzz211423
 */


public class View {
    static UserDao userDao=new UserDaoImpl();
    public static Scanner scanner = new Scanner(System.in);

    public static String chose() {
        String chose = scanner.nextLine();
        return chose;
    }

    public static void mainView() {
        System.out.println("*****************");
        System.out.println();
        System.out.println("请选择登录或者注册新用户:");
        System.out.println();
        System.out.println("1.登录");
        System.out.println();
        System.out.println("2.注册");
        System.out.println();
        System.out.println("*****************");
        switch (chose()) {
            case "1":
                Login();
                break;
            case "2":
                userRegisterView();
                break;
            default:
                System.out.println("输入有误，请重新输入：");
        }
    }

    public static void Login() {
        System.out.println("*****************");
        System.out.println();
        System.out.println("请选择登录身份:");
        System.out.println();
        System.out.println("1.普通用户");
        System.out.println();
        System.out.println("2.管理员");
        System.out.println();
        System.out.println("*****************");
        switch (chose()) {
            case "1":
                userLoginView();
                break;
            case "2":
                adminLoginView();
                break;
            default:
                System.out.println("输入有误，请重新输入：");
        }
    }

    public static void userLoginView() {
        System.out.println("*****************");
        System.out.println();
        System.out.println("请输入用户名:");
        String user_name = scanner.nextLine();
        System.out.println();
        System.out.println("请输入密码:");
        String password = scanner.nextLine();
        System.out.println();
        System.out.println("*****************");
        User user=userDao.selectUser(user_name);
        if ( user!=null&&user.getUser_password().equals(password)){
            System.out.println("登录成功！");
            System.out.println("您可以开始选购商品啦！");
            shopping();
        }else{
            System.out.println("用户名或密码有误，请检查！");
            userLoginView();
        }

    }

    public static void adminLoginView() {
        System.out.println("*****************");
        System.out.println();
        System.out.println("请输入管理员用户名:");
        System.out.println();
        System.out.println("请输入密码:");
        System.out.println();
        System.out.println("*****************");
    }

    public static void userRegisterView() {
        System.out.println("*****************");
        System.out.println();
        System.out.println("请输入用户名:");
        System.out.println();
        System.out.println("请输入密码:");
        System.out.println();
        System.out.println("请输入您的手机号:");
        System.out.println();
        System.out.println("请输入您的收货地址:");
        System.out.println();
        System.out.println("*****************");
    }
    public static void shopping(){
        System.out.println("请选择查看商品的方式：1.默认排序 2.按销量排序");
        switch (chose()){
            case "1":
                userDao.lookProduct();
                break;
            case "2":
                userDao.lookProductForSaleNum();
                break;
            default:
                System.out.println("输入有误，请重新输入：");
                shopping();
        }

    }

}
