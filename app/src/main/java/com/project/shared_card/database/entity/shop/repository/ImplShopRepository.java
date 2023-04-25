package com.project.shared_card.database.entity.shop.repository;

import com.project.shared_card.database.entity.shop.ShopDao;
import com.project.shared_card.database.entity.shop.ShopEntity;
import com.project.shared_card.retrofit.model.Shop;

import java.util.List;

public class ImplShopRepository implements ShopRepository{
    ShopDao shopDao;

    public ImplShopRepository(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    @Override
    public void addShop(List<ShopEntity> shopEntity) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                shopDao.insertShop(shopEntity);
            }
        });
        thread.start();
    }
}
