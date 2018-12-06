package com.lixavier.manage.convertor;

import com.lixavier.manage.model.Address;
import com.lixavier.manage.req.AddressDTO;
import com.lixavier.manage.resp.AddressODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层地址转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-02>
 * @Modify Date: <2018-12-02>
 * @Version: <1.0>
 */
public abstract class AddressConvertor {

    private static final BeanCopier beanCopierForAddressODTO = BeanCopier.create(Address.class, AddressODTO.class, false);
    private static final BeanCopier beanCopierForAddress = BeanCopier.create(AddressDTO.class, Address.class, false);

    public static AddressODTO toAddressODTO(Address address) {
        if (address == null) {
            return null;
        }
        AddressODTO addressODTO = new AddressODTO();
        beanCopierForAddressODTO.copy(address, addressODTO, null);
        addressODTO.setChildren(AddressConvertor.toAddressODTOList(address.getChildren()));
        return addressODTO;
    }

    public static Address toAddress(AddressDTO addressDTO) {
        Address address = new Address();
        beanCopierForAddress.copy(addressDTO, address, null);
        return address;
    }

    public static List<AddressODTO> toAddressODTOList(List<Address> addressList) {
        if (addressList == null || addressList.isEmpty()) {
            return null;
        }
        List<AddressODTO> addressInfoList = new ArrayList<AddressODTO>(addressList.size());
        for (Address address : addressList) {
            addressInfoList.add(toAddressODTO(address));
        }
        return addressInfoList;
    }

    public static List<Address> toAddressList(List<AddressDTO> addressDTOList) {
        if (addressDTOList == null || addressDTOList.isEmpty()) {
            return null;
        }
        List<Address> addressList = new ArrayList<Address>(addressDTOList.size());
        for (AddressDTO addressDTO : addressDTOList) {
            addressList.add(toAddress(addressDTO));
        }
        return addressList;
    }

    public static QueryResultODTO<AddressODTO> toQueryResult(QueryResultODTO<Address> queryResult) {
        QueryResultODTO<AddressODTO> queryResultInfo = new QueryResultODTO<AddressODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toAddressODTOList(queryResult.getRecords()));
        return queryResultInfo;
        }
}