package com.iweb.pro_test.view;


/**
 * @Author 小火煮粥
 * @date 2023/7/17 22:10
 * @WeChat xhzz211423
 */


public class View {

    public void mainView() {
        System.out.println("*****************");
        System.out.println();
        System.out.println("请选择登录或者注册新用户:");
        System.out.println();
        System.out.println("1.登录");
        System.out.println();
        System.out.println("2.注册");
        System.out.println();
        System.out.println("*****************");
    }

    public void Login() {
        System.out.println("*****************");
        System.out.println();
        System.out.println("请选择登录身份:");
        System.out.println();
        System.out.println("1.普通用户");
        System.out.println();
        System.out.println("2.管理员");
        System.out.println();
        System.out.println("*****************");
    }

    public void userLoginView() {
        System.out.println("*****************");
        System.out.println();
        System.out.println("请输入用户名:");
        System.out.println();
        System.out.println("请输入密码:");
        System.out.println();
        System.out.println("*****************");
    }

    public void adminLoginView(){
        System.out.println("*****************");
        System.out.println();
        System.out.println("请输入管理员用户名:");
        System.out.println();
        System.out.println("请输入密码:");
        System.out.println();
        System.out.println("*****************");
    }

    public void userRegisterView(){
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

    public void adminRegisterView(){
        System.out.println("*****************");
        System.out.println();
        System.out.println("请输入管理员用户名:");
        System.out.println();
        System.out.println("请输入密码:");
        System.out.println();
        System.out.println("*****************");
    }
}
