package com.smbms.service;

import com.smbms.pojo.Bill;

import java.util.List;

public interface BillService {


    List<Bill> findBillList(Integer queryIsPayment,String queryProductName, Long queryProviderId);

    Bill selectBillById(Long billid);


    int updateBillById(Bill bill);

    int addBillBy(Bill bill);

    int deletebill(Long billid);
}
