package com.iweb.view;


import com.iweb.DAO.impl.AdminDAOImpl;
import com.iweb.DAO.UserDAO;
import com.iweb.DAO.impl.UserDAOImpl;
import com.iweb.clazzs.Admin;
import com.iweb.clazzs.User;
import com.iweb.control.Controller;
import com.iweb.DAO.AdminDAO;
import com.iweb.service.AdminService;
import com.iweb.service.UserService;

import java.util.Scanner;

/**
 * @Author 小火煮粥
 * @date 2023/7/17 22:10
 * @WeChat xhzz211423
 */


public class View {
    //    提供静态Scanner类方变从键盘获取输入
    static Scanner sc = new Scanner(System.in);
    //    提供接口实现类对象 调用方法
    static UserDAO userDAO = new UserDAOImpl();
    static AdminDAO adminDAO = new AdminDAOImpl();
   public static User currentUser;

    //主界面
    public static void mainView() {
        System.out.println("*****************");
        System.out.println("欢迎来到IWEB商城");
        System.out.println("请选择登录或者注册新用户:");
        System.out.println("1.登录");
        System.out.println("2.注册");
        System.out.println("*****************");
        String key = sc.nextLine();
        Controller.mainController(key);
    }

    //选择登录界面
    public static void Login() {
        System.out.println("*****************");
        System.out.println("请选择登录身份:");
        System.out.println("1.普通用户");
        System.out.println("2.管理员");
        System.out.println("*****************");
        String key = sc.nextLine();
         Controller.loginController(key);
    }

    //普通用户登录界面
    public static void userLoginView() {
        System.out.println("******用户登录******");
        System.out.println("请输入用户名:");
        String userName = sc.nextLine();
        System.out.println("请输入密码:");
        String userPassword = sc.nextLine();
        User user = new User();
        user.setUser_name(userName);
        user.setUser_password(userPassword);
//        判断是否登录成功
        if (!UserService.isUser(user)){
            System.out.println("用户名或密码错误,重新输入");
            View.userLoginView();
        }
//        for (User u:userDAO.listAllUser()){
//            if(u.getUser_name().equals(user.getUser_name())){
//                user=u;
//            }
//        }
        userLoginSuccess(currentUser);
    }

    //    用户登录成功界面
    public static void userLoginSuccess(User user) {
        System.out.println("*****************");
        System.out.println("请选择你要查看的信息:");
        System.out.println("1.账户余额");
        System.out.println("2.商品信息");
        System.out.println("3.购物车");
        System.out.println("4.订单信息");
        System.out.println("5.返回主界面");
        System.out.println("*****************");
        String key = sc.nextLine();
        Controller.userSuccessController(key, user);
    }

    // 是否充值余额
    public static void userCharge(User user) {
        System.out.println("*****************");
        System.out.println("请选择是否充值");
        System.out.println("1.充值      2.返回");
        System.out.println("*****************");
        String key=sc.nextLine();
        Controller.userChargeController(key,user);
    }

    //查看商品信息方式
    public static void checkProductMethod(User user) {
        System.out.println("*****************");
        System.out.println("请选择你要查看商品的方式:");
        System.out.println("1.默认排序");
        System.out.println("2.按销量排序");
        System.out.println("*****************");
        String key = sc.nextLine();
        Controller.checkProductController(user,key);

    }

    //管理员登录界面
    public static void adminLoginView() {
        System.out.println("*****************");
        System.out.println();
        System.out.println("请输入管理员用户名:");
        String adminName = sc.nextLine();
        System.out.println("请输入密码:");
        String adminPassword = sc.nextLine();
        Admin admin = new Admin();
        admin.setAdmin_name(adminName);
        admin.setAdmin_password(adminPassword);
        AdminService.isAdmin(admin);
        adminLoginSuccess();
    }

    //    管理员登录成功界面
    public static void adminLoginSuccess() {
        System.out.println("*****************");
        System.out.println("请选择你要进行的操作");
        System.out.println("1.增加商品");
        System.out.println("2.删除商品");
        System.out.println("3.修改商品信息");
        System.out.println("4.查看商品信息");
        System.out.println("5.查看订单信息");
        System.out.println("6.修改订单信息");
        System.out.println("7.返回主界面");
        System.out.println("*****************");
        String key = sc.nextLine();
        Controller.adminSuccessController(key);
    }

    //选择注册界面
    public static void RegisterView() {
        System.out.println("*****************");
        System.out.println();
        System.out.println("请选择注册身份:");
        System.out.println();
        System.out.println("1.普通用户");
        System.out.println();
        System.out.println("2.管理员");
        System.out.println();
        System.out.println("*****************");
        String key = sc.nextLine();
        Controller.registerController(key);
    }

    //普通用户注册界面
    public static void userRegisterView() {
        System.out.println("*****************");
        System.out.println("请输入用户名:");
        String userName = sc.nextLine();     //键盘获取用户信息
        System.out.println("请输入密码:");
        String userPassword = sc.nextLine();
        System.out.println("请输入您的手机号:");
        String userPhone = sc.nextLine();
        System.out.println("请输入您的收货地址:");
        String userAddress = sc.nextLine();
        User user = new User();     //  新建用户对象存储用户信息
        user.setUser_name(userName);
        user.setUser_password(userPassword);
        user.setUser_phone(userPhone);
        user.setUser_address(userAddress);
        if (UserService.isRegister(user)){//是否注册成功
            userLoginView();
        }
        userDAO.userRegister(user);   // 调用userRegister方法进行用户注册
        System.out.println("*****************");
    }

    //管理员注册界面
    public static void adminRegisterView() {
        System.out.println("*****************");
        System.out.println("请输入管理员用户名:");
        String adminName = sc.nextLine();     //键盘获取管理员信息
        System.out.println("请输入密码:");
        String adminPassword = sc.nextLine();
        System.out.println("请输入手机号:");
        String adminPhone = sc.nextLine();
        Admin admin = new Admin();     //新建管理员对象存储信息
        admin.setAdmin_name(adminName);
        admin.setAdmin_password(adminPassword);
        admin.setAdmin_phone(adminPhone);
        adminDAO.adminRegister(admin);     //实现管理员注册
        AdminService.isRegister(admin);
        System.out.println("*****************");
    }

}
