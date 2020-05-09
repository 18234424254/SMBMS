package com.smbms.service;


import com.smbms.pojo.Bill;
import com.smbms.pojo.Provider;

import java.util.List;

public interface ProviderService {


    List<Provider> selectProListBy(String queryProName, String queryProCode);
    //查询该列表返回bill
    List<Provider> selectBillProListBy();

    Provider selectProListById(Long id);

    int updateProById(Provider provider);

    int insertProBy(Provider provider);

    List<Bill> selectProBillListById(Long proid);

}
