package com.smbms.service;

import com.smbms.dao.BillMapper;
import com.smbms.dao.ProviderMapper;
import com.smbms.pojo.Bill;
import com.smbms.pojo.BillExample;
import com.smbms.pojo.Provider;
import com.smbms.pojo.ProviderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    @Qualifier("providerMapper")
    private ProviderMapper providerMapper;
    @Autowired
    private BillMapper billMapper;
    //查询供应商列表
    @Override
    public List<Provider> selectProListBy(String queryProName, String queryProCode) {
        ProviderExample example = new ProviderExample();
        ProviderExample.Criteria criteria = example.createCriteria();

        if(queryProCode!=null){
            criteria.andProCodeLike("%"+queryProCode+"%");
        }
        if(queryProName!=null){
            criteria.andProNameLike("%"+queryProName+"%");
        }
        List<Provider> providers = providerMapper.selectByExample(example);
        return providers;
    }

    /**
     * 反馈给Bill列表
     * @return
     */
    @Override
    public List<Provider> selectBillProListBy() {
        ProviderExample example = new ProviderExample();
        List<Provider> providerList = providerMapper.selectByExample(example);
        return providerList;
    }

    @Override
    public Provider selectProListById(Long id) {
        Provider provider = providerMapper.selectByPrimaryKey(id);
        return provider;
    }

    @Override
    public int updateProById(Provider provider) {
        ProviderExample example = new ProviderExample();
        ProviderExample.Criteria criteria = example.createCriteria();
        criteria.andProNameEqualTo(provider.getProName());
        return providerMapper.updateByExampleSelective(provider, example);

    }
    //增加用户
    @Override
    public int insertProBy(Provider provider) {
        return providerMapper.insertSelective(provider);
    }

    @Override
    public List<Bill> selectProBillListById(Long proid) {
        BillExample example = new BillExample();
        BillExample.Criteria criteria = example.createCriteria();
        criteria.andProviderIdEqualTo(proid);
        List<Bill> billList = billMapper.selectByExample(example);
        return null;
    }
}
