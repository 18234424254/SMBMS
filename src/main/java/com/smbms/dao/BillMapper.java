package com.smbms.dao;

import com.smbms.pojo.Bill;
import com.smbms.pojo.BillExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BillMapper {
    long countByExample(BillExample example);

    int deleteByExample(BillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Bill record);

    int insertSelective(Bill record);

    List<Bill> selectByExample(BillExample example);

    Bill selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Bill record, @Param("example") BillExample example);

    int updateByExample(@Param("record") Bill record, @Param("example") BillExample example);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);


    @Select("select b.*,p.proName from smbms_bill b,smbms_provider p where p.id=b.providerId and b.id=#{id}")
    Bill selectBillById(Long id);




}