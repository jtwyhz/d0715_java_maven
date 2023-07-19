package com.iweb.DAO.impl;

import com.iweb.DAO.ShopCarDAO;
import com.iweb.Until.DBUtil;

import java.sql.*;

public class ShopCarDAOimpl implements ShopCarDAO {
    //    根据 用户id 商品id  显示商品数量
    @Override
    public int select(Integer user_id, Integer property_id) {
        int count = 0;
        String sql = "select product_num from shopcar where user_id=? and shopcar_id=?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setString(1, String.valueOf(user_id));
            ps.setInt(2, property_id);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }


    //    向购物车 插入订单数据
    @Override
    public void insert(Integer shop_carid,Integer user_id,Integer product_id,
                       String product_prce,String product_num,Integer sum_price) {
        int count = 0;
        String sql = "insert into shopcar values(?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement
                     (sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setInt(1,shop_carid);
            ps.setInt(2,user_id);
            ps.setInt(3,product_id);
            ps.setString(4,product_prce);
            ps.setString(5,product_num);
            ps.setInt(6,sum_price);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //    根据用户id 商品id 删除商品
    @Override
    public void delete(Integer user_id,Integer property_id) {
        String sql = "delete from shopcar where user_id and product_id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, user_id);
            ps.setInt(1, property_id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //   根据 用户id 商品id 更新商品数量
    @Override
    public void update(int count,int pid,int uid) {
        String sql = "update shopcar set product_num=? where user_id=? and shopcar_id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1,count);
            ps.setInt(2,uid);
            ps.setInt(3,pid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
