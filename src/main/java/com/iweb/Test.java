package com.iweb;


import com.iweb.DAO.AdminDAO;
import com.iweb.DAO.UserDAO;
import com.iweb.DAO.impl.AdminDAOImpl;
import com.iweb.DAO.impl.UserDAOImpl;
import com.iweb.clazzs.Product;
import com.iweb.clazzs.User;
import com.iweb.control.Controller;
import com.iweb.view.View;

import java.util.Collection;

/**
 * @Author 小火煮粥
 * @date 2023/7/19 19:48
 * @WeChat xhzz211423
 */


public class Test {
    public static void main(String[] args) {
//        View.mainView();
//        User user=new User(1);
//        UserDAO userDAO=new UserDAOImpl();
//        System.out.println(userDAO.checkOrder(user));
//        userDAO.checkMoney(user);
//        Product product=new Product();
//        product.setProduct_name("茅台");
//        product.setProduct_price(99.0);
//        product.setStock_num(1234);
//        product.setSales_num(8888);
//        product.setProduct_id(2);
//        product.setAdmin_id(3);
//        AdminDAO adminDAO=new AdminDAOImpl();
//        adminDAO.addProduct(product);
        Controller.adminSuccessController("6");
    }
}
