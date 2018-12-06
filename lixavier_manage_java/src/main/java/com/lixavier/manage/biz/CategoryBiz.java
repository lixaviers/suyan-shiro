package com.lixavier.manage.biz;

import com.lixavier.manage.dao.CategoryMapper;
import com.lixavier.manage.model.Category;
import com.github.pagehelper.PageInfo;
import com.lixavier.manage.resp.QueryResultODTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层类目管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("categoryBiz")
public class CategoryBiz {
    private final Logger logger = LoggerFactory.getLogger(CategoryBiz.class);

    @Autowired
    CategoryMapper categoryMapper;


    @Transactional(readOnly = true)
    public Category getCategory(Long id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        return category;
    }

    @Transactional(readOnly = true)
    public List<Category> getCategoryList() {
        QueryResultODTO<Category> queryResult = new QueryResultODTO<Category>();
        return categoryMapper.getCategoryList();
    }


}