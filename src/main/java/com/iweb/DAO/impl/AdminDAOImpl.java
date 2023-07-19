package com.iweb.DAO.impl;

import com.iweb.DAO.AdminDAO;
import com.iweb.Until.DBUtil;
import com.iweb.clazzs.Admin;
import com.iweb.clazzs.Order;
import com.iweb.clazzs.Product;
import com.iweb.view.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author tang
 * @date 2023/7/17 18:52
 */
public class AdminDAOImpl implements AdminDAO {
    Connection connection;

    {
        try {
            connection = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adminRegister(Admin admin) {
        String sql = "insert into admin(admin_name,admin_password,admin_phone) values(?,?,?)";
        //        判断用户名 密码 手机号是否为空
        if (admin.getAdmin_name() == null || admin.getAdmin_name().equals("") ||
                admin.getAdmin_password() == null || admin.getAdmin_password().equals("") ||
                admin.getAdmin_phone().equals("") || admin.getAdmin_phone() == null) {
            System.out.println("用户名或密码不能为空");
        }
        //        连接数据库 进行数据插入
        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            //            为每个?设置相应的值
            ps.setString(1, admin.getAdmin_name());
            ps.setString(2, admin.getAdmin_password());
            ps.setString(3, admin.getAdmin_phone());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Admin> listAllAdmin() {
//        创建集合存储管理员对象
        List<Admin> list = new ArrayList<>();
        String sql = "select * from admin";
        try (
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
//            sql语句的执行结果存入到结果集中
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAdmin_name(rs.getString("admin_name"));
                admin.setAdmin_password(rs.getString("admin_password"));
                admin.setAdmin_phone(rs.getString("admin_phone"));
//                管理员对象添加到集合中
                list.add(admin);
            }
        } catch (Exception e) {

        }
//        判断集和是否为空 做空值处理
        return list.isEmpty() ? null : list;
    }

    @Override
    public void addProduct(Product product) {

        String sql = "select product_name from product";
        String sql1 = "INSERT INTO product(product_name,product_price,stock_num,sales_num,property_id,admin_id)" +
                " VALUES(?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             PreparedStatement ps1 = c.prepareStatement(sql1);
        ) {
            ResultSet rs = ps.executeQuery();
            boolean isExist = true;
            while (rs.next()) {
                if (product.getProduct_name().equals(rs.getString(1))) {
                    System.out.println("商品已经存在");
                    isExist = false;
                    break;
                }
            }
            if (isExist) {
                ps1.setString(1, product.getProduct_name());
                ps1.setDouble(2, product.getProduct_price());
                ps1.setInt(3, product.getStock_num());
                ps1.setInt(4, product.getSales_num());
                ps1.setInt(5, product.getProperty_id());
                ps1.setInt(6, product.getAdmin_id());
                ps1.execute();
                System.out.println("添加成功");
            }
            View.adminLoginSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int pid) {
        String sql = "select product_id from product";
        String sql1 = "delete from product where product_id=" + pid;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             PreparedStatement ps1 = c.prepareStatement(sql1)
        ) {
            ResultSet rs = ps.executeQuery();
            boolean isExist = true;
            while (rs.next()) {
                if (pid == rs.getInt(1)) {
                    ps1.execute();
                    isExist = false;
                    System.out.println("删除成功");
                    break;
                }
            }
            if (isExist) {
                System.out.println("商品不存在");
            }
            View.adminLoginSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeProduct(int pid, double pr, int st, int sa) {
        String sql = "select product_id from product";
        String sql1 = "update product set product_price=?,stock_num=?,sales_num=? where product_id=" + pid;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             PreparedStatement ps1 = c.prepareStatement(sql1)
        ) {
            ResultSet rs = ps.executeQuery();
            boolean isExist = true;
            while (rs.next()) {
                if (pid == rs.getInt(1)) {
                    ps1.setDouble(1, pr);
                    ps1.setInt(2, st);
                    ps1.setInt(3, sa);
                    ps1.execute();
                    System.out.println("修改成功");
                    isExist = false;
                    break;
                }
            }
            if (isExist) {
                System.out.println("商品不存在");
            }
            View.adminLoginSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Product> checkProduct() {
        String sql = "SELECT product_id,product_name,product_price,stock_num,sales_num" +
                ",product.property_id,property_name,property_describe,admin_id FROM product,property order by sales_num desc limit 0,10";
        List<Product> list = new ArrayList<>();
        try {
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
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
            for (Product p : list
            ) {
                System.out.println(p);
            }
            View.adminLoginSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.isEmpty() ? null : list;
    }

    @Override
    public Collection<Order> checkOrder() {
        String sql = "select * from orders";
        List<Order> list = new ArrayList<>();
        try {
            Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrder_num(rs.getInt(1));
                order.setProduct_id(rs.getInt(3));
                order.setProduct_name(rs.getString(4));
                order.setProduct_price(rs.getDouble(5));
                order.setProduct_num(rs.getInt(6));
                order.setOrder_state(rs.getString(7));
                list.add(order);
            }
            for (Order o:list
                 ) {
                System.out.println(o);
            }
            View.adminLoginSuccess();
        } catch (Exception e) {

        }
        return list.isEmpty()?null:list;
    }

    @Override
    public void changeOrder(int oid) {

    }
}
