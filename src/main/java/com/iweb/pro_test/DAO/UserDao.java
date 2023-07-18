package com.iweb.pro_test.DAO;
import com.iweb.pro_test.clazzs.*;


import java.util.Collection;

/**
 * @author tang
 * @date 2023/7/17 18:47
 */
public interface UserDao {
    void insert(User user);
    /**包含id无法注册，登录则验证密码
     * @return 返回所有用户id结果集
     *
     */
    Collection<User> selectUsers();

    /**返回单个用户全部信息
     * @param user 用户
     * @return
     */
    User selectUser(User user);

    /**返回某个用户的购物车信息
     * @return
     */
    ShopCar selectCar(User user);

    /**查看所有商品（系统推荐），返回商品集合，
     * @return
     */
    Collection<Product> lookProduct();

    /**按照商品销量进行降序排序展示
     * @return
     */
    Collection<Product> lookProductForSaleNum();
    /**将商品添加到购物车
     * @param user 为哪个用户的购物车添加
     * @param product 某件商品
     * @return
     */
    void addProductToCar(User user, Product product);

    /**将商品从购物车中删除
     * @param user 为哪个用户的购物车添加
     * @param product 某件商品，要求用户提供商品id
     * @return
     */
    void deleteProductFromCar(User user, Product product);

    /** 返回某个用户的所有地址
     * @param user
     * @return
     */
    Address selectAddress(User user);

    /**需要添加的地址
     * @param user 某个用户
     * @param address
     * @return
     */
    void insertAddress(User user, Address address);

    /**需要删除的用户地址，要求用户提供地址id
     * @param user
     * @param address
     */
    void deleteAddress(User user, Address address);

    /**返回用户的订单信息
     * @param user
     * @return
     */
    Order selectOrder(User user);

    /**购物车结算，生成订单
     * @param user 用户
     * @param order 用户
     */
    void addOrder(User user, Order order,Product product);


}
