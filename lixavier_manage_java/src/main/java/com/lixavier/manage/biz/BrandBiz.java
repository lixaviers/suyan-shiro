package com.lixavier.manage.biz;

import com.lixavier.manage.dao.BrandMapper;
import com.lixavier.manage.model.Brand;
import com.lixavier.manage.model.BrandExample;
import com.lixavier.manage.req.BrandQueryDTO;
import com.github.pagehelper.PageHelper;
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
 * @Comments: <业务层品牌管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("brandBiz")
public class BrandBiz {
    private final Logger logger = LoggerFactory.getLogger(BrandBiz.class);

    @Autowired
    BrandMapper brandMapper;


    @Transactional(readOnly = true)
    public List<Brand> getBrandList() {
        BrandExample example = new BrandExample();
        example.createCriteria().andIsDeletedEqualTo(false);
        example.setOrderByClause("order_num,id");
        return brandMapper.selectByExample(example);
    }


}