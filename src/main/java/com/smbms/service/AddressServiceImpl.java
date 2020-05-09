package com.smbms.service;

import com.smbms.dao.AddressMapper;
import com.smbms.pojo.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    @Qualifier("addressMapper")
    private AddressMapper addressMapper;
    @Override
    public Integer updateAddressBy(Address address) {
        return addressMapper.updateByPrimaryKeySelective(address);
    }
}
