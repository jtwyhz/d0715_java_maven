package com.iweb.control;


import com.iweb.DAO.UserDAO;
import com.iweb.DAO.impl.UserDAOImpl;
import com.iweb.entry.User;
import com.iweb.view.View;

/**
 * @Author 小火煮粥
 * @date 2023/7/18 13:50
 * @WeChat xhzz211423
 */


public class Controller {
    static UserDAO userDAO=new UserDAOImpl();
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

    public static void userSuccessController(String key ,User user){
        switch (key){
            case "1":
                userDAO.checkMoney(user);
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            default:
                break;
        }
    }
    public static void adminSuccessController(String key){
        switch (key){
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
