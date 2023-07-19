package com.iweb.DAO;

import com.iweb.clazzs.Admin;
import com.iweb.clazzs.Order;
import com.iweb.clazzs.Product;

import java.util.Collection;

/**
 * @author tang
 * @date 2023/7/17 18:52
 */
public interface AdminDAO {
    /**
     * 管理员注册
     *
     * @param admin 提供管理员信息
     */
    void adminRegister(Admin admin);

    /**
     * 显示所有用户信息 用于登录和注册判断
     *
     * @return 返回所有管理员集合
     */
    Collection<Admin> listAllAdmin();

    /**
     * 增加商品信息
     *
     * @param product 传入商品对象
     */
    void addProduct(Product product);

    /**
     * 删除商品信息
     *
     * @param pid 删除的商品的id
     */
    void deleteProduct(int pid);


    /**修改商品信息
     * @param pid 要修改的商品id
     * @param pr  修改价格
     * @param st 修改库存
     * @param sa 修改销量
     */
    void changeProduct(int pid,double pr,int st,int sa);

    /**
     * 查看所有商品
     *
     * @return 返回商品集合
     */
    Collection<Product> checkProduct();

    /**
     * 查看订单信息
     *
     * @return 返回订单集合
     */
    Collection<Order> checkOrder();


    /**修改订单信息
     * @param oid 要修改订单的id
     * @param state 修改状态
     */
    void changeOrder(int oid,String state);

}
