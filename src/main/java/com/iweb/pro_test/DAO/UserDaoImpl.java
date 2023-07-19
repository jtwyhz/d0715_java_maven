package com.iweb.pro_test.DAO;

import com.iweb.pro_test.entry.*;
import com.iweb.pro_test.Until.DBUtil;
import com.iweb.pro_test.view.View;
import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;


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
    public User selectUser(String user_name) {
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
    public Address getAddress(int user_id, String detail,int address_id) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM address WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int user_id1 = resultSet.getInt("user_id");
                String detail1 = resultSet.getString("detail");
                int address_id1 = resultSet.getInt("address_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void insertAddress(Address address, int address_id,int user_id,String detail) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO address(address_id, user_id, detail) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,address_id);
            statement.setInt(2, Integer.parseInt(detail));
            statement.setString(3, String.valueOf(user_id));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void deleteAddress(int id) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "DELETE FROM address WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Order selectOrder(User user) {
        return null;
    }


    @Override
    public void addOrder(User user, Order order, Product product) {
        String selectSumPrice = "select sum_price from shopcar";
        String selectProductNum = "select count(*) num from shopcar";
        String selectMoney = "select money from user where user_id=?";
        String insertToOrder = "insert into order(user_id,product_id,product_name,product_price," +
                "product_num,order_sum_price,order_state) values (?,?,?,?,?,?,?)";
        double sum_price = 0.0;
        double money = 0.0;
        int productNum = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSumPrice);
            ResultSet sumPrice = preparedStatement.executeQuery();
            while (sumPrice.next()) {
                sum_price = sumPrice.getDouble("sum_price");
            }
            preparedStatement = connection.prepareStatement(selectProductNum);
            ResultSet num = preparedStatement.executeQuery();
            while (sumPrice.next()) {
                productNum = sumPrice.getInt("num");
            }
            preparedStatement = connection.prepareStatement(selectMoney);
            preparedStatement.setInt(1, user.getUser_id());
            ResultSet reMoney = preparedStatement.executeQuery();
            while (reMoney.next()) {
                money = reMoney.getDouble("money");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("您当前账户余额为：" + money + "元");
        System.out.println("您需要支付的商品总金额为：" + sum_price + "元，是否确认支付？y/n");
        if (money < sum_price) {
            System.out.println("当前余额不足，");
        }
        if (scanner.nextLine().equals("y")) {
            System.out.println("支付成功！");
            try {
                preparedStatement = connection.prepareStatement(insertToOrder);
//                设置对应参数
                preparedStatement.setInt(1, user.getUser_id());
                preparedStatement.setInt(2, product.getProduct_id());
                preparedStatement.setString(3, product.getProduct_name());
                preparedStatement.setDouble(4, product.getProduct_price());
                preparedStatement.setInt(5, productNum);
                preparedStatement.setDouble(6, sum_price);
                preparedStatement.setString(7, "待发货");
                preparedStatement.execute();
                System.out.println("即将返回首页······");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                跳转首页
                View.shopping();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
