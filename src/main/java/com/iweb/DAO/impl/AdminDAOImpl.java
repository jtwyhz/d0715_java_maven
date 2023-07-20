package com.iweb.DAO.impl;

import com.iweb.DAO.AdminDAO;
import com.iweb.Until.DBUtil;
import com.iweb.clazzs.Admin;
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
}
