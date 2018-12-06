package com.lixavier.manage.biz;

import com.lixavier.manage.dao.GoodsDescriptionMapper;
import com.lixavier.manage.model.GoodsDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层商品描述管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("goodsDescriptionBiz")
public class GoodsDescriptionBiz {
    private final Logger logger = LoggerFactory.getLogger(GoodsDescriptionBiz.class);

    @Autowired
    GoodsDescriptionMapper goodsDescriptionMapper;

    /**
     * 创建商品描述
     *
     * @param goodsDescription
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createGoodsDescription(GoodsDescription goodsDescription) {
        goodsDescriptionMapper.insert(goodsDescription);
        return goodsDescription.getGoodsId();
    }

    /**
     * 更新商品描述
     *
     * @param goodsDescription
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer updateGoodsDescription(GoodsDescription goodsDescription) {
        GoodsDescription goodsDescriptionLast = goodsDescriptionMapper.selectByPrimaryKeyForUpdate(goodsDescription.getGoodsId());
        if (goodsDescriptionLast == null) {
            createGoodsDescription(goodsDescription);
            return 1;
        }
        return goodsDescriptionMapper.updateByPrimaryKey(goodsDescription);
    }

}