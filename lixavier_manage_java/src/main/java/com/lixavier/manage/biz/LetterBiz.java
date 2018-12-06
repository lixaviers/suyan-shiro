package com.lixavier.manage.biz;

import com.lixavier.manage.constant.LetterStatusEnum;
import com.lixavier.manage.constant.LetterTypeEnum;
import com.lixavier.manage.dao.LetterMapper;
import com.lixavier.manage.model.GiftCard;
import com.lixavier.manage.model.Letter;
import com.lixavier.manage.model.UserLetter;
import com.lixavier.manage.req.LetterQueryDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixavier.manage.resp.QueryResultODTO;
import org.apache.ibatis.annotations.Lang;
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
 * @Comments: <业务层站内信管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("letterBiz")
public class LetterBiz {
    private final Logger logger = LoggerFactory.getLogger(LetterBiz.class);

    @Autowired
    LetterMapper letterMapper;

    @Autowired
    UserLetterBiz userLetterBiz;

    /**
     * 逻辑删除站内信
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer deleteLetter(Long id) {
        // TODO: Describe business logic and implement it
        int result = letterMapper.logicalDeleteByPrimaryKey(id);
        return result;
    }

    /**
     * 创建站内信
     *
     * @param letter
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createLetter(Letter letter) {
        // TODO: Describe business logic and implement it
        letterMapper.insertSelective(letter);
        return letter.getId();
    }

    /**
     * 创建礼品卡站内信
     *
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createLetter(Letter letter, Long userId) {
        letter.setIsDeleted(false);
        letter.setCreateTime(null);
        letter.setUpdateTime(null);
        letterMapper.insertSelective(letter);

        UserLetter userLetter = new UserLetter();
        userLetter.setUserId(userId);
        userLetter.setLetterId(letter.getId());
        userLetter.setIsRead(false);
        userLetterBiz.createUserLetter(userLetter);

        return letter.getId();
    }

    /**
     * 批量创建
     *
     * @param letterList
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public int batchCreateLetter(List<Letter> letterList) {
        // TODO: Describe business logic and implement it
        return letterMapper.insertBatch(letterList);
    }

    /**
     * 更新站内信
     *
     * @param letter
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer updateLetter(Letter letter) {
        Integer result = null;
        Letter letterLast = letterMapper.selectByPrimaryKeyForUpdate(letter.getId());
        if (letterLast == null) {
            // TODO：这里请写清楚
        }
        // TODO: Describe business logic and implement it
        result = letterMapper.updateByPrimaryKeySelective(letter);
        return result;
    }

    /**
     * 根据ID获取站内信信息
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public Letter getLetter(Long id) {
        // TODO: Describe business logic and implement it
        Letter letter = letterMapper.selectByPrimaryKey(id);
        return letter;
    }

    /**
     * 分页查询站内信信息
     *
     * @param letterQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<Letter> queryLetter(LetterQueryDTO letterQuery) {
        QueryResultODTO<Letter> queryResult = new QueryResultODTO<Letter>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(letterQuery.getPageNo(), letterQuery.getPageSize());
        List<Letter> letterList = letterMapper.queryLetter(letterQuery);
        PageInfo<Letter> pageInfo = new PageInfo<Letter>(letterList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(letterList);
        return queryResult;
    }

}