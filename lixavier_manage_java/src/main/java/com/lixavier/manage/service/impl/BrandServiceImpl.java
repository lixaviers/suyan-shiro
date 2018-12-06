package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.BrandBiz;
import com.lixavier.manage.convertor.BrandConvertor;
import com.lixavier.manage.model.Brand;
import com.lixavier.manage.resp.BrandODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.service.IBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 品牌管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("brandService")
public class BrandServiceImpl implements IBrandService {
    private final Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);

    @Autowired
    private BrandBiz brandBiz;

    @Override
    public Result<List<BrandODTO>> getBrandList() {
        Result<List<BrandODTO>> result = Result.newSuccess();
        List<Brand> resultInfo = brandBiz.getBrandList();
        result.setDataMap(BrandConvertor.toBrandODTOList(resultInfo));
        return result;
    }
}