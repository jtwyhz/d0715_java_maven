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
                userDAO.checkMoney(user);
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
            case "5":
                View.mainView();
            default:
                break;
        }
    }
public static void userChargeController(String key,User user){
        switch (key){
            case "1":
                userDAO.userCharge(user);
            case "2":
                View.userLoginSuccess(user);
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
                System.out.println("是否购买商品 y/n");
                String choice1=sc.nextLine();
                if(choice1.equals("y")){
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
                  View.userLoginSuccess(user);
                break;
            default:
                break;
        }
    }

    public static void adminSuccessController(String key) {
        switch (key) {
            case "1":
                Product product=new Product();
                System.out.println("*****************");
                System.out.println("请输入添加的商品名字");
                product.setProduct_name(sc.next());
                System.out.println("请输入添加的商品价格（double）");
                product.setProduct_price(sc.nextDouble());
                System.out.println("请输入添加的商品库存");
                product.setStock_num(sc.nextInt());
                System.out.println("请输入添加的商品销量");
                product.setSales_num(sc.nextInt());
                System.out.println("请输入添加的商品属性id");
                product.setProperty_id(sc.nextInt());
                System.out.println("请输入添加的商品管理员id");
                product.setAdmin_id(sc.nextInt());
                adminDAO.addProduct(product);
                break;
            case "2":
                System.out.println("请输入要删除的商品id");
                int id =sc.nextInt();
                adminDAO.deleteProduct(id);
                break;
            case "3":
                System.out.println("请输入要修改商品id");
                int pid=sc.nextInt();
                System.out.println("输入修改后的价格");
                double ppr=sc.nextDouble();
                System.out.println("输入修改后的库存");
                int pst=sc.nextInt();
                System.out.println("输入修改后的销量");
                int psa=sc.nextInt();
                adminDAO.changeProduct(pid,ppr,pst,psa);
                break;
            case "4":
                adminDAO.checkProduct();
                break;
            case "5":
                adminDAO.checkOrder();
                break;
            case "6":
                System.out.println("请输入要修改订单id");
                int oid=sc.nextInt();
                System.out.println("输入修改后的状态");
                String state=sc.next();
                adminDAO.changeOrder(oid,state);
                break;
            case "7":
                View.mainView();
            default:
                break;
        }

    }
}
