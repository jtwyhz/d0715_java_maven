package com.iweb.pro_test.DAO;

import com.iweb.pro_test.clazzs.*;
import com.iweb.pro_test.clazzs.Address;
import com.iweb.pro_test.Until.DBUtil;
import com.iweb.pro_test.view.View;

import java.sql.*;
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
    private Object Savepoint;

    @Override
    public void updateUserMoney(User user) {
        
    }

    {
        try {
            connection = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(User user) {

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
            statement.setInt(1,address.getAddress_id());
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
            String sql = "delete FROM address WHERE id = ?";
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
    public void addOrder(User user) {
        String selectProduct_name = "select product_name from product where product_id=?";
        String selectProduct = "select * from shopcar where user_id=?";
        String selectMoney = "select money from user where user_id=?";
        String insertToOrder = "insert into orders(user_id,product_id,product_name,product_price," +
                "product_num,order_sum_price,order_state) values (?,?,?,?,?,?,?)";
        double sum_price = 0.0;
        double money = 0.0;
        try {
            //            查看用户账户余额
            preparedStatement = connection.prepareStatement(selectMoney);
            preparedStatement.setInt(1, user.getUser_id());
            ResultSet reMoney = preparedStatement.executeQuery();
            while (reMoney.next()) {
                money = reMoney.getDouble("money");
            }
            preparedStatement = connection.prepareStatement(selectProduct);
            preparedStatement.setInt(1,user.getUser_id());
            //查询购物车中所有商品
            ResultSet num = preparedStatement.executeQuery();
            int product_id = 0;
            String product_name = "";

//            遍历每一件商品
           Savepoint savepoint= connection.setSavepoint("ins");
            while (num.next()) {
                sum_price = sum_price + num.getDouble(6);
                product_id = num.getInt(3);
                preparedStatement = connection.prepareStatement(selectProduct_name);
                preparedStatement.setInt(1,num.getInt("product_id"));
                ResultSet p_name = preparedStatement.executeQuery();
//                获得该商品的商品名
                while (p_name.next()) {
                    product_name = p_name.getString(1);
                }
                preparedStatement = connection.prepareStatement(insertToOrder);
                insertToOrder(user.getUser_id(), product_id, product_name, num.getDouble(4), num.getInt(5),
                        num.getDouble(6), "待发货");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("您当前账户余额为：" + money + "元");
        System.out.println("您需要支付的商品总金额为：" + sum_price + "元，是否确认支付？y/n");
        if (money < sum_price) {
            System.out.println("当前余额不足");
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (scanner.nextLine().equals("y")) {
            System.out.println("支付成功！");
//            更新账户余额
            user.setMoney(user.getMoney() - sum_price);
            updateUserMoney(user);
            System.out.println("即将返回首页······");
            try {
                connection.close();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            View.shopping();
        }
    }

    public void insertToOrder(int user_id, int product_id, String product_name, double product_price, int productNum, double sum_price, String state) {
        try {
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, product_id);
            preparedStatement.setString(3, product_name);
            preparedStatement.setDouble(4, product_price);
            preparedStatement.setInt(5, productNum);
            preparedStatement.setDouble(6, sum_price);
            preparedStatement.setString(7, state);
            preparedStatement.execute();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }
}
