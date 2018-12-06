package com.lixavier.manage.dao;

import com.lixavier.manage.model.Address;

import java.util.List;

public interface AddressMapper {
    

    Address getAddress(Integer id);

    List<Address> getAddressList();



}