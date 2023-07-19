package com.iweb.DAO;
import com.iweb.clazzs.*;


import java.util.Collection;

/**
 * @author tang
 * @date 2023/7/17 18:47
 */
public interface UserDAO {


    /**
     * 注册新用户
     *
     * @param user 提供用户信息
     */
    void userRegister(User user);

    /**
     * 显示所有用户信息 用于登录和注册判断
     *
     * @return 返回所有用户集合
     */
    Collection<User> listAllUser();


    /**查看用户余额
     * @param user
     */
    void checkMoney(User user);

    /**用户购买商品后更新余额
     * @param user 用户
     */
    void updateUserMoney(User user);


    /**查看购物车
     * @param user 用户
     * @return 返回某个用户的购物车信息
     */
    ShopCar selectCar(User user);

    /**查看所有商品（系统推荐），
     * @return 返回商品集合，
     */
    Collection<Product> lookProduct();

    /**按照商品销量进行降序排序展示
     * @return 所有商品
     */
    Collection<Product> lookProductForSaleNum();

    /**将商品添加到购物车
     * @param user 为哪个用户的购物车添加
     * @param product 某件商品
     */
    void addProductToCar(User user, Product product);

    /**将商品从购物车中删除
     * @param user 为哪个用户的购物车添加
     * @param product 某件商品，要求用户提供商品id
     */
    void deleteProductFromCar(User user, Product product);


    /**需要添加的地址
     * @param user_id 用户
     * @param address 地址
     */
    void insertAddress(Address address, int Address_id, int user_id, String detail);

    /** 获得地址
     * @param user_id
     * @param detail
     * @param address_id
     * @return
     */
    Address getAddress(int user_id, String detail, int address_id);

    /**需要删除的用户地址，要求用户提供地址id
     * @param id 用户
     * @param
     */
    void deleteAddress(int id);

    /**返回用户的订单信息
     * @param user 用户
     * @return 返回订单的所有信息
     */
    Order selectOrder(User user);

    /**购物车结算，生成订单
     * @param user 用户
     */
    void addOrder(User user, Order order, Product product);
}
