package com.lixavier.manage.biz;

import com.lixavier.manage.constant.LetterTypeEnum;
import com.lixavier.manage.dao.UserLetterMapper;
import com.lixavier.manage.exception.CommonBizException;
import com.lixavier.manage.model.UserLetter;
import com.lixavier.manage.model.UserLetterExample;
import com.lixavier.manage.req.UserLetterQueryDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixavier.manage.resp.EnumODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.page.UserLetterPageODTO;
import com.lixavier.manage.result.ResultCode;
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
 * @Comments: <业务层用户站内信管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("userLetterBiz")
public class UserLetterBiz {
    private final Logger logger = LoggerFactory.getLogger(UserLetterBiz.class);

    @Autowired
    UserLetterMapper userLetterMapper;

    /**
     * 删除用户站内信
     *
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer deleteUserLetter(List<Long> idList) {

        UserLetterExample example = new UserLetterExample();
        example.createCriteria().andIdIn(idList);
        userLetterMapper.deleteByExample(example);

        return 1;
    }

    /**
     * 创建用户站内信
     *
     * @param userLetter
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createUserLetter(UserLetter userLetter) {
        userLetterMapper.insertSelective(userLetter);
        return userLetter.getId();
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public boolean read(List<Long> idList) {
        UserLetter record = new UserLetter();
        record.setIsRead(true);
        UserLetterExample example = new UserLetterExample();
        example.createCriteria().andIdIn(idList);
        userLetterMapper.updateByExampleSelective(record, example);
        return true;
    }

    /**
     * 分页查询用户站内信信息
     *
     * @param userLetterQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<UserLetterPageODTO> queryUserLetter(UserLetterQueryDTO userLetterQuery) {
        QueryResultODTO<UserLetterPageODTO> queryResult = new QueryResultODTO<UserLetterPageODTO>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(userLetterQuery.getPageNo(), userLetterQuery.getPageSize());
        List<UserLetterPageODTO> userLetterList = userLetterMapper.queryUserLetter(userLetterQuery);
        if (CollectionUtils.isNotEmpty(userLetterList)) {
            for (UserLetterPageODTO odto : userLetterList) {
                odto.setTypeDesc(LetterTypeEnum.getDescByValue(odto.getType()));
            }
        }
        PageInfo<UserLetterPageODTO> pageInfo = new PageInfo<UserLetterPageODTO>(userLetterList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(userLetterList);
        return queryResult;
    }


    @Transactional(readOnly = true)
    public List<EnumODTO> getUserLetterCount(Long userId) {
        return userLetterMapper.getUserLetterCount(userId);
    }

    @Transactional(readOnly = true)
    public Integer getUserNotReadLetterCount(Long userId) {
        UserLetterExample example = new UserLetterExample();
        example.createCriteria().andIsReadEqualTo(false).andUserIdEqualTo(userId);
        List<UserLetter> userLetters = userLetterMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(userLetters)) {
            return userLetters.size();
        }
        return 0;
    }


}