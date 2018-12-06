package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.AddressBiz;
import com.lixavier.manage.convertor.AddressConvertor;
import com.lixavier.manage.resp.AddressODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 地址管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-02>
 * @Modify Date: <2018-12-02>
 * @Version: <1.0>
 */
@Service("addressService")
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressBiz addressBiz;

    @Override
    public Result<List<AddressODTO>> getAddressList() {
        Result<List<AddressODTO>> result = Result.newSuccess();
        result.setDataMap(AddressConvertor.toAddressODTOList(addressBiz.getAddressList()));
        return result;
    }


}