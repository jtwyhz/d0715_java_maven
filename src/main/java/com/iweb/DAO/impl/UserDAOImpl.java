package com.iweb.DAO.impl;

import com.iweb.DAO.UserDAO;
import com.iweb.clazzs.*;
import com.iweb.Until.DBUtil;
import com.iweb.view.View;

import java.sql.*;
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
        try (
                PreparedStatement ps = connection.prepareStatement(sql)
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
        String sql = "update user set money=? where user_id=?";
        try {
            connection.prepareStatement(sql);
            preparedStatement.setDouble(1, user.getMoney());
            preparedStatement.setInt(2, user.getUser_id());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void checkMoney(User user) {
        String sql = "select * from user where user_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, user.getUser_id());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                double money = rs.getDouble("money");
                System.out.println("余额为:" + money);
            }
            View.userCharge(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ShopCar checkCar(User user) {
        String sql = "select * from shopcar where user_id=?";
        ShopCar shopCar = new ShopCar();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getUser_id());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                shopCar.setShopCar_id(rs.getInt(1));
                shopCar.setUser_id(rs.getInt(2));
                shopCar.setProduct_id(rs.getInt(3));
                shopCar.setProduct_price(rs.getDouble(4));
                shopCar.setProduct_num(rs.getInt(5));
                shopCar.setSum_price(rs.getDouble(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shopCar;
    }


    @Override
    public Collection<Product> lookProduct() {
        String sql = "SELECT product_id,product_name,product_price,stock_num,sales_num" +
                ",product.property_id,property_name,property_describe,admin_id FROM product,property limit 0,10";
        List<Product> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getInt(1));
                product.setProduct_name(rs.getString(2));
                product.setProduct_price(rs.getDouble(3));
                product.setStock_num(rs.getInt(4));
                product.setSales_num(rs.getInt(5));
                product.setProperty_id(rs.getInt(6));
                product.setProperty_name(rs.getString(7));
                product.setProperty_describe(rs.getString(8));
                product.setAdmin_id(rs.getInt(9));
                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.isEmpty() ? null : list;
    }

    @Override
    public Collection<Product> lookProductForSaleNum() {
        String sql = "SELECT product_id,product_name,product_price,stock_num,sales_num" +
                ",product.property_id,property_name,property_describe,admin_id FROM product,property order by sales_num desc limit 0,10";
        List<Product> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getInt(1));
                product.setProduct_name(rs.getString(2));
                product.setProduct_price(rs.getDouble(3));
                product.setStock_num(rs.getInt(4));
                product.setSales_num(rs.getInt(5));
                product.setProperty_id(rs.getInt(6));
                product.setProperty_name(rs.getString(7));
                product.setProperty_describe(rs.getString(8));
                product.setAdmin_id(rs.getInt(9));
                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.isEmpty() ? null : list;
    }

    @Override
    public void addProductToCar(User user, Product product) {
        String haveTheProduct = "select product_num,sum_price from shopcar where product_id=?";
        String addToCar = "insert into shopcar(user_id,product_id,product_price,product_num,sum_price) values (?,?,?,?,?)";
        String updateTheCar = "update shopcar set product_num=?,sum_price=? where product_id=?";
        int productNum = 0;
        double sumPrice = 0.0;
        try {
            preparedStatement = connection.prepareStatement(haveTheProduct);
            preparedStatement.setInt(1, product.getProduct_id());
            ResultSet resultSet = preparedStatement.executeQuery();
//          查看购物车中是否存在该商品，存在则更新，不存在直接插入
            if (resultSet.next()) {
                productNum = resultSet.getInt(1);
                sumPrice = resultSet.getDouble(2);
                preparedStatement = connection.prepareStatement(updateTheCar);
                preparedStatement.setInt(1, productNum + 1);
                preparedStatement.setDouble(2, sumPrice + product.getProduct_price());
                preparedStatement.setInt(3, product.getProduct_id());
                preparedStatement.execute();
            } else {
                preparedStatement = connection.prepareStatement(addToCar);
                preparedStatement.setInt(1, user.getUser_id());
                preparedStatement.setInt(2, product.getProduct_id());
                preparedStatement.setDouble(3, product.getProduct_price());
                preparedStatement.setInt(4, 1);
                preparedStatement.setDouble(5, product.getProduct_price());
                preparedStatement.execute();
            }
            System.out.println("添加完成！");
//            System.out.println("请选择：1.继续添加  2.查看购物车 3.浏览商品");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteProductFromCar(int user_id, int product_id) {
        String deleteProduct = "delete from shopcar where user_id=? and product_id=?";
        try {
            preparedStatement = connection.prepareStatement(deleteProduct);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, product_id);
            preparedStatement.execute();
            System.out.println("删除成功！");
//            System.out.println("请选择：1.浏览商品  2.查看购物车  3.继续删除");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public Address getAddress(int user_id, String detail, int address_id) {
        try {
            String sql = "SELECT * FROM address WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("需要获取的地址是：");
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int user_id1 = resultSet.getInt("user_id");
                String detail1 = resultSet.getString("detail");
                int address_id1 = resultSet.getInt("address_id");
                System.out.println("用户id：" + user_id1);
                System.out.println("地址：" + detail1);
                System.out.println("地址id：" + address_id1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertAddress(Address address, int address_id, int user_id, String detail) {
        try {
            String sql = "INSERT INTO address(address_id, user_id, detail) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("你需要插入的地址是：");
            statement.setInt(1, address_id);
            statement.setInt(2, Integer.parseInt(detail));
            statement.setString(3, String.valueOf(user_id));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void deleteAddress(int id) {
        try {
            String sql = "DELETE FROM address WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("你需要删除的地址是：");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Order checkOrder(User user) {
        String sql = "select * from orders where user_id=?";
        Order order = new Order();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getUser_id());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            order.setOrder_num(rs.getInt(1));
            order.setProduct_id(rs.getInt(3));
            order.setProduct_name(rs.getString(4));
            order.setProduct_price(rs.getDouble(5));
            order.setProduct_num(rs.getInt(6));
            order.setOrder_state(rs.getString(7));
        } catch (Exception e) {

        }
        return order;
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
            preparedStatement.setInt(1, user.getUser_id());
            //查询购物车中所有商品
            ResultSet num = preparedStatement.executeQuery();
            int product_id = 0;
            String product_name = "";

//            遍历每一件商品
            Savepoint savepoint = connection.setSavepoint("ins");
            while (num.next()) {
                sum_price = sum_price + num.getDouble(6);
                product_id = num.getInt(3);
                preparedStatement = connection.prepareStatement(selectProduct_name);
                preparedStatement.setInt(1, num.getInt("product_id"));
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
                preparedStatement.close();
                connection.close();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            View.shopping();
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

    @Override
    public void userCharge(User user) {
        String sql = "select * from user where user_id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, user.getUser_id());
            ResultSet rs = ps.executeQuery();
            rs.next();
            double money = rs.getDouble("money");
            System.out.println("您当前的余额为：" + money);
            System.out.println("充值金额：");
            Scanner sc = new Scanner(System.in);
            double inMoney = sc.nextDouble();
            if (inMoney < 0) {
                System.out.println("请输入正确的金额");
                userCharge(user);
            } else {
                String sql1 = "update user set money=money+" + inMoney + "where user_id=?";
                PreparedStatement ps1 = connection.prepareStatement(sql1);
                ps1.setInt(1, user.getUser_id());
                ps1.execute();
                System.out.println("充值成功");
                money += inMoney;
                System.out.println("您当前的余额为：" + money);
                View.userCharge(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
