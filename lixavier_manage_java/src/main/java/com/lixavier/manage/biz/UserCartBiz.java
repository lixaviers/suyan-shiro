package com.lixavier.manage.biz;

import com.lixavier.manage.constant.GoodsStatusEnum;
import com.lixavier.manage.dao.UserCartMapper;
import com.lixavier.manage.model.Goods;
import com.lixavier.manage.model.UserCart;
import com.lixavier.manage.model.UserCartExample;
import com.lixavier.manage.req.UserCartQueryDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixavier.manage.resp.GoodsODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层用户购物车管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
@Service("userCartBiz")
public class UserCartBiz {
    private final Logger logger = LoggerFactory.getLogger(UserCartBiz.class);

    @Autowired
    UserCartMapper userCartMapper;

    /**
     * 逻辑删除用户购物车
     *
     * @param idList
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer deleteUserCart(List<Long> idList) {
        if (CollectionUtils.isNotEmpty(idList)) {
            for (Long id : idList) {
                userCartMapper.logicalDeleteByPrimaryKey(id);
            }
        }
        return 1;
    }

    /**
     * 创建用户购物车
     *
     * @param userCart
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createUserCart(UserCart userCart) {

        UserCartExample example = new UserCartExample();
        example.createCriteria().andIsDeletedEqualTo(false).andUserIdEqualTo(userCart.getUserId()).andGoodsIdEqualTo(userCart.getGoodsId());
        List<UserCart> userCarts = userCartMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(userCarts)) {
            UserCart userCartLast = userCarts.get(0);
            userCartMapper.updateNumber(userCartLast.getId(), userCart.getNumber());
            return 1l;
        } else {
            userCart.setCreateTime(null);
            userCart.setUpdateTime(null);
            userCart.setIsDeleted(false);
            userCartMapper.insertSelective(userCart);
            return userCart.getId();
        }

    }

    /**
     * 分页查询用户购物车信息
     *
     * @param userCartQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<Goods> queryUserCart(UserCartQueryDTO userCartQuery) {
        QueryResultODTO<Goods> queryResult = new QueryResultODTO<Goods>();
        userCartQuery.setGoodsStatus(GoodsStatusEnum.NORMAL.getValue());
        // 使用分页插件PageHelper实现分页功能
//        PageHelper.startPage(userCartQuery.getPageNo(), userCartQuery.getPageSize());
        List<Goods> userCartList = userCartMapper.queryUserCart(userCartQuery);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(userCartList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(userCartList);
        return queryResult;
    }

}