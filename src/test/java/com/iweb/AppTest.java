package com.iweb;

import static org.junit.Assert.assertTrue;

import com.iweb.DAO.UserDAO;
import com.iweb.DAO.impl.UserDAOImpl;
import com.iweb.clazzs.Product;
import com.iweb.clazzs.User;
import com.iweb.control.Controller;
import com.iweb.view.View;
import org.junit.Test;

import java.util.Collection;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    @Test
    public void Settle(){
        UserDAO userDAO=new UserDAOImpl();
//        userDAO.checkMoney(new User("小明"));
//        Controller.checkProductController("2");
//        View.checkProductMethod();
//        Controller.checkProductController("1");
       System.out.println( userDAO.lookProductForSaleNum() );
//        System.out.println(userDAO.checkCar(new User(1)));
//        System.out.println(userDAO.checkOrder(new User(1)));
        userDAO.userCharge(new User(1));
    }
}
