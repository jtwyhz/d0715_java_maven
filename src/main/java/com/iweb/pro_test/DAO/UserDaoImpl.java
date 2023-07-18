package com.iweb.pro_test.DAO;

import com.iweb.pro_test.clazzs.*;
import com.iweb.pro_test.Until.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

/**
 * @author tang
 * @date 2023/7/17 18:47
 */
public class UserDaoImpl implements UserDao {
    Connection connection;
    PreparedStatement preparedStatement;
    Scanner scanner = new Scanner(System.in);

    @Override
    public void insert(User user) {

    }

    {
        try {
            connection = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<User> selectUsers() {
        return null;
    }

    @Override
    public User selectUser(User user) {
        return null;
    }

    @Override
    public ShopCar selectCar(User user) {
        return null;
    }

    @Override
    public Collection<Product> lookProduct() {
        return null;
    }

    @Override
    public Collection<Product> lookProductForSaleNum() {
        return null;
    }

    @Override
    public void addProductToCar(User user, Product product) {

    }

    @Override
    public void deleteProductFromCar(User user, Product product) {

    }

    @Override
    public Address selectAddress(User user) {
        return null;
    }

    @Override
    public void insertAddress(User user, Address address) {

    }

    @Override
    public void deleteAddress(User user, Address address) {

    }

    @Override
    public Order selectOrder(User user) {
        return null;
    }


    @Override
    public void addOrder(User user, Order order,Product product) {
        String selectSumPrice = "select sum_price from shopcar";
        String selectMoney = "select money from user where user_id=?";
        String insertToOrder = "insert into order(user_id,product_id,product_name,product_price," +
                "product_num,order_sum_price,order_state) values (?,?,?,?,?,?,?)";
        Double sum_price = 0.0;
        Double money = 0.0;
        try {
            preparedStatement = connection.prepareStatement(selectSumPrice);
            ResultSet sumPrice = preparedStatement.executeQuery();
            while (sumPrice.next()) {
                sum_price = sumPrice.getDouble("sum_price");
            }
            preparedStatement = connection.prepareStatement(selectMoney);
            ResultSet reMoney = preparedStatement.executeQuery();
            while (reMoney.next()) {
                money = reMoney.getDouble("money");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("您当前账户余额为："+money+"元");
        System.out.println("您需要支付的商品总金额为：" + sum_price + "元，是否确认支付？y/n");
        if (money<sum_price){
            System.out.println("当前余额不足，");
        }
        if (scanner.nextLine().equals("y")) {
            System.out.println("支付成功！");
            try {
                preparedStatement = connection.prepareStatement(insertToOrder);
//                设置对应参数
//                preparedStatement.setInt(1, user.getUser_id());
//                preparedStatement.setInt(2, order.getOrder_num());
//                preparedStatement.setInt(3, product.getProduct_name());
//                preparedStatement.setInt(4, );
//                preparedStatement.setInt(5, );
//                preparedStatement.setInt(6, );
//                preparedStatement.setInt(7, );
                preparedStatement.execute();
                System.out.println("即将返回首页······");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                跳转首页

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
