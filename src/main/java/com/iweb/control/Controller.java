package com.iweb.control;


import com.iweb.DAO.AdminDAO;
import com.iweb.DAO.UserDAO;
import com.iweb.DAO.impl.AdminDAOImpl;
import com.iweb.DAO.impl.UserDAOImpl;
import com.iweb.clazzs.Admin;
import com.iweb.clazzs.Product;
import com.iweb.clazzs.User;
import com.iweb.view.View;

import java.util.Scanner;

/**
 * @Author 小火煮粥
 * @date 2023/7/18 13:50
 * @WeChat xhzz211423
 */


public class Controller {
    static UserDAO userDAO = new UserDAOImpl();
    static AdminDAO adminDAO=new AdminDAOImpl();
    static Scanner sc=new Scanner(System.in);
    public static void mainController(String key) {
        switch (key) {
            case "1":
                View.Login();
                break;
            case "2":
                View.RegisterView();
                break;
            default:
                System.out.println("输入错误，请重新输入");
                break;
        }
    }

    public static void loginController(String key) {
        switch (key) {
            case "1":
                View.userLoginView();
                break;
            case "2":
                View.adminLoginView();
                break;
            default:
                break;
        }
    }

    public static void registerController(String key) {
        switch (key) {
            case "1":
                View.userRegisterView();
                break;
            case "2":
                View.adminRegisterView();
                break;
            default:
                break;
        }
    }

    public static void userSuccessController(String key, User user) {
        switch (key) {
            case "1":
                userDAO.userCharge(user);
                break;
            case "2":
                View.checkProductMethod(user);
                break;
            case "3":
                userDAO.checkCar(user);
                break;
            case "4":
                System.out.println(userDAO.checkOrder(user));
                break;
            default:
                break;
        }
    }

    public static void checkProductController(User user,String key) {
        switch (key) {
            case "1":
//                System.out.println(userDAO.lookProduct());
                for (Product p:userDAO.lookProduct()
                     ) {
                    System.out.println(p);
                }
                System.out.println("是否购买商品 y/n");
                String choice=sc.nextLine();
                if(choice.equals("y")){
                    System.out.println("请输入商品id");
                    int pid =sc.nextInt();
                    Product product = new Product();
                    product.setProduct_id(pid);
                    userDAO.addProductToCar(user,product);
                    View.userLoginSuccess(user);
                    break;
                }else {
                    View.userLoginSuccess(user);
                }
//                userDAO.addProductToCar();
                break;
            case "2":
//                System.out.println(userDAO.lookProductForSaleNum());
                for (Product p:userDAO.lookProductForSaleNum()
                     ) {
                    System.out.println(p);
                }
                break;
            default:
                break;
        }
    }

    public static void adminSuccessController(String key) {
        switch (key) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            default:
                break;
        }

    }
}
