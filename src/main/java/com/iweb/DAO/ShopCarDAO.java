package com.iweb.DAO;

public interface ShopCarDAO {

    //    根据 用户id 商品id  显示商品数量
    int select(Integer user_id, Integer property_id);
    //    向购物车 插入订单数据
    void insert(Integer shop_carid,Integer user_id,Integer product_id,
                Integer product_prce,String product_num,String sum_price);
    //    根据用户id 商品id 删除商品
    void delete(Integer user_id,Integer property_id);
    //   根据 用户id 商品id 更新商品数量
    void update(int count,int pid,int uid);
}
