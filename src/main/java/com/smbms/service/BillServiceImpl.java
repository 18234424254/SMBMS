package com.smbms.service;

import com.smbms.dao.BillMapper;
import com.smbms.dao.ProviderMapper;
import com.smbms.pojo.Bill;
import com.smbms.pojo.BillExample;
import com.smbms.pojo.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    @Qualifier("billMapper")
    private BillMapper billMapper;
    @Autowired
    private ProviderMapper providerMapper;

    /**
     * 综合查询bill列表
     * @param queryIsPayment
     * @param queryProductName
     * @param queryProviderId
     * @return
     */
    @Override
    public List<Bill> findBillList(Integer queryIsPayment,String queryProductName, Long queryProviderId) {
        BillExample example = new BillExample();
        BillExample.Criteria criteria = example.createCriteria();
        if (queryIsPayment!=null&&queryIsPayment>0){
           criteria.andIsPaymentEqualTo(queryIsPayment);
        }
        if (queryProductName!=null){
            criteria.andProductNameLike("%"+queryProductName+"%");
        }
        if (queryProviderId!=null&&queryProviderId>0){
            criteria.andProviderIdEqualTo(queryProviderId);
        }
        List<Bill> billList = billMapper.selectByExample(example);
        for (Bill bill : billList) {
            Provider provider = providerMapper.selectByPrimaryKey(bill.getProviderId());
            bill.setProName(provider.getProName());
        }
        return billList;
    }
    //根据id查询bill列表详情
    @Override
    public Bill selectBillById(Long billid) {
        Bill bill = billMapper.selectBillById(billid);
        return bill;
    }
    //修改bill信息
    @Override
    public int updateBillById(Bill bill) {
        return billMapper.updateByPrimaryKeySelective(bill);
    }

    @Override
    public int addBillBy(Bill bill) {


        return billMapper.insertSelective(bill);
    }

    @Override
    public int deletebill(Long billid) {
        return billMapper.deleteByPrimaryKey(billid);
    }

}
