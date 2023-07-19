package com.iweb.DAO.impl;

import com.iweb.DAO.UserDAO;
import com.iweb.clazzs.Order;
import com.iweb.clazzs.Product;
import com.iweb.clazzs.ShopCar;
import com.iweb.clazzs.User;
import com.iweb.Until.DBUtil;
import com.iweb.view.View;
import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * @author tang
 * @date 2023/7/17 18:47
 */
public class UserDAOImpl implements UserDAO {
    Connection connection;
    PreparedStatement preparedStatement;
    Scanner scanner = new Scanner(System.in);



    {
        try {
            connection = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void userRegister(User user) {
//        定义sql插入语句
        String sql = "insert into user(user_name,user_password,user_phone,user_address) values(?,?,?,?)";
//        判断用户名 密码是否为空
        if (user.getUser_name() == null || user.getUser_name().equals("") || user.getUser_password().equals("")) {
            System.out.println("用户名或密码不能为空");
        }
//        判断电话号码 地址信息是否为空
        if (user.getUser_address() == null || user.getUser_address().equals("") ||
                user.getUser_phone() == null || user.getUser_phone().equals("")) {
            System.out.println("请检查电话号或地址信息");
        }
//        连接数据库 进行数据插入
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
//            为每个?设置相应的值
            ps.setString(1, user.getUser_name());
            ps.setString(2, user.getUser_password());
            ps.setString(3, user.getUser_phone());
            ps.setString(4, user.getUser_address());
            ps.execute();
        } catch (Exception e) {

        }
    }

    @Override
    public Collection<User> listAllUser() {
//        创建集合存储用户对象
        List<User> list = new ArrayList<>();
        String sql = "select * from user";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
//            讲查询的执行结果存到结果集中
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUser_name(rs.getString("user_name"));
                user.setUser_password(rs.getString("user_password"));
                user.setUser_phone(rs.getString("user_phone"));
                user.setUser_address(rs.getString("user_address"));
//                将用户加到集合中
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //        判断集和是否为空 做空值处理
        return list.isEmpty() ? null : list;
    }

    @Override
    public void updateUserMoney(User user) {

    }

    @Override
    public void checkMoney(User user) {
        String sql = "select * from user where user_name='?'";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, user.getUser_name());
            ResultSet rs = ps.executeQuery();
            rs.next();
            double money = rs.getDouble("money");
            System.out.println("余额为:" + money);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ShopCar selectCar(User user) {
        return null;
    }

    @Override
    public void insertAddress(com.iweb.clazzs.Address address, int Address_id, int user_id, String detail) {

    }

    @Override
    public Collection<Product> lookProduct() {
        return  null;
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
    public Address getAddress(int user_id, String detail, int address_id) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM address WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            System.out.println("需要获取的地址是：");
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int user_id1 = resultSet.getInt("user_id");
                String detail1 = resultSet.getString("detail");
                int address_id1 = resultSet.getInt("address_id");
                System.out.println("用户id："+user_id1);
                System.out.println("地址："+detail1);
                System.out.println("地址id："+address_id1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void insertAddress(Address address, int address_id, int user_id, String detail) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO address(address_id, user_id, detail) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            System.out.println("你需要插入的地址是：");
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
            System.out.println("你需要删除的地址是：");
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
