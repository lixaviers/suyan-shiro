package com.lixavier.manage.biz;

import com.lixavier.manage.dao.AddressMapper;
import com.lixavier.manage.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层地址管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-02>
 * @Modify Date: <2018-12-02>
 * @Version: <1.0>
 */
@Service("addressBiz")
public class AddressBiz {
    private final Logger logger = LoggerFactory.getLogger(AddressBiz.class);

    @Autowired
    AddressMapper addressMapper;

    @Transactional(readOnly = true)
    public Address getAddress(Integer id) {
        return addressMapper.getAddress(id);
    }

    @Transactional(readOnly = true)
    public List<Address> getAddressList() {
        return addressMapper.getAddressList();
    }

}