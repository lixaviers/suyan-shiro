package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.UserCartBiz;
import com.lixavier.manage.convertor.GoodsConvertor;
import com.lixavier.manage.convertor.UserCartConvertor;
import com.lixavier.manage.model.Goods;
import com.lixavier.manage.model.UserCart;
import com.lixavier.manage.req.UserCartDTO;
import com.lixavier.manage.req.UserCartQueryDTO;
import com.lixavier.manage.resp.GoodsODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.UserCartODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.service.IUserCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lixavier.manage.validate.UserCartValidate.validateForCreate;
import static com.lixavier.manage.validate.UserCartValidate.validateForUpdate;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 用户购物车管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
@Service("userCartService")
public class UserCartServiceImpl implements IUserCartService {
    private final Logger logger = LoggerFactory.getLogger(UserCartServiceImpl.class);

    @Autowired
    private UserCartBiz userCartBiz;


    @Override
    public Result<Integer> deleteUserCart(List<Long> idList) {
        Result<Integer> result = Result.newSuccess();
        result.setDataMap(userCartBiz.deleteUserCart(idList));
        return result;
    }

    @Override
    public Result<Long> createUserCart(UserCartDTO userCartDTO) {
        Result<Long> result = Result.newSuccess();
        UserCart userCart = UserCartConvertor.toUserCart(userCartDTO);
        if (!validateForCreate(userCart, result)) {
            return result;
        }
        result.setDataMap(userCartBiz.createUserCart(userCart));
        return result;
    }

    @Override
    public Result<Integer> batchCreate(List<UserCartDTO> userCartDTOs) {
        Result<Integer> result = Result.newSuccess();
        List<UserCart> items = UserCartConvertor.toUserCartList(userCartDTOs);
        result.setDataMap(userCartBiz.batchCreateUserCart(items));
        return result;
    }

    @Override
    public Result<Integer> updateUserCart(UserCartDTO userCartDTO) {
        Result<Integer> result = Result.newSuccess();
        UserCart userCart = null;
        userCart = UserCartConvertor.toUserCart(userCartDTO);
        if (!validateForUpdate(userCart, result)) {
            return result;
        }
        result.setDataMap(userCartBiz.updateUserCart(userCart));
        return result;
    }

    @Override
    public Result<QueryResultODTO<GoodsODTO>> queryUserCart(UserCartQueryDTO userCartQueryDTO) {
        Result<QueryResultODTO<GoodsODTO>> result = Result.newSuccess();
        QueryResultODTO<Goods> resultInfo = userCartBiz.queryUserCart(userCartQueryDTO);
        result.setDataMap(GoodsConvertor.toQueryResult(resultInfo));
        return result;
    }
}