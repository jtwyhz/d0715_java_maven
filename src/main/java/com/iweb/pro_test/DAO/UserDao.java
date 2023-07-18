package com.iweb.pro_test.DAO;
import com.iweb.pro_test.clazzs.*;


import java.util.Collection;

/**
 * @author tang
 * @date 2023/7/17 18:47
 */
public interface UserDao {
    /**用户注册
     * @param user 用户
     */
    void insert(User user);

    /**包含用户名，无法注册，登录则验证密码
     * @param user_name 通过用户名查找是否存在该用户
     * @return 返回所有用户id结果集
     */
    User selectUser(String user_name);

    /**查看用户信息
     * @param user 用户
     * @return 返回单个用户全部信息
     */
    User selectUser(User user);

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

    /** 查看地址
     * @param user 用户
     * @return 返回某个用户的所有地址
     */
    Collection<Address> selectAddress(User user);

    /**需要添加的地址
     * @param user 某个用户
     * @param address 地址
     */
    void insertAddress(User user, Address address);

    /**需要删除的用户地址，要求用户提供地址id
     * @param user 用户
     * @param address 地址
     */
    void deleteAddress(User user, Address address);

    /**返回用户的订单信息
     * @param user 用户
     * @return 返回订单的所有信息
     */
    Order selectOrder(User user);

    /**购物车结算，生成订单
     * @param user 用户
     * @param order 订单
     * @param product 商品
     */
    void addOrder(User user, Order order,Product product);


}
