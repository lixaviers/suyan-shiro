package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.CategoryBiz;
import com.lixavier.manage.convertor.CategoryConvertor;
import com.lixavier.manage.model.Category;
import com.lixavier.manage.resp.CategoryODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.service.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 类目管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {
    private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryBiz categoryBiz;


    public Result<List<CategoryODTO>> getCategoryList() {
        Result<List<CategoryODTO>> result = Result.newSuccess();
        List<Category> resultInfo = categoryBiz.getCategoryList();
        result.setDataMap(CategoryConvertor.toCategoryODTOList(resultInfo));
        return result;
    }
}