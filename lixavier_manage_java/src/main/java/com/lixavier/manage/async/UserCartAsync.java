package com.lixavier.manage.async;

import com.lixavier.manage.dao.UserCartMapper;
import com.lixavier.manage.model.UserCart;
import com.lixavier.manage.req.OrderGoodsDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userCartAsync")
public class UserCartAsync {
    private final Logger logger = LoggerFactory.getLogger(UserCartAsync.class);

    @Autowired
    private UserCartMapper userCartMapper;


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Async
    public Boolean createGiftCardLetter(List<OrderGoodsDTO> goodsList, Long userId) {
        if (CollectionUtils.isNotEmpty(goodsList)) {
            goodsList.forEach(goods -> {
                UserCart bean = new UserCart();
                bean.setUserId(userId);
                bean.setGoodsId(goods.getGoodsId());
                bean.setNumber(goods.getGoodsNumber());
                userCartMapper.deleteUserCarts(bean);
            });
        }
        return true;
    }


}