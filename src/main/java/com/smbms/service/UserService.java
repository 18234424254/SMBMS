package com.smbms.service;

import com.github.pagehelper.PageInfo;
import com.smbms.pojo.User;

import java.util.List;


public interface UserService {
    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById(Long id)throws Exception;

    User login(String userCode,String password)throws Exception;



    int insertUser(User user)throws Exception;

    User findUserCode(String userCode);

    int uplodUserById(User user)throws Exception;

    int deleteUserByid(Long l)throws Exception;

    PageInfo<User> selectUserListByYe(Integer pageNum, int pageSize, String queryname,Long queryUserRole)throws Exception;

    int selectUserPassword(User user)throws Exception;
}
