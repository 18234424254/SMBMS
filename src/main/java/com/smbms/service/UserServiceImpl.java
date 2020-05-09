package com.smbms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smbms.dao.RoleMapper;
import com.smbms.dao.UserMapper;
import com.smbms.pojo.Role;
import com.smbms.pojo.RoleExample;
import com.smbms.pojo.User;
import com.smbms.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据id查找用户详情
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public User findUserById(Long id)throws Exception {
        return userMapper.selectUserById(id);
    }

    /**
     * 用户登录查询
     * @param userCode
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public User login(String userCode, String password) throws Exception {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(userCode);
        criteria.andUserPasswordEqualTo(password);

        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() < 1) {
            return null;
        }
        return userList.get(0);
    }



    /**
     * 增加用户
     * @return
     */
    @Override
    public int insertUser(User user)throws Exception{
        return  userMapper.insert(user);

    }

    /**
     * ajax方法
     * @param userCode
     * @return
     */
    @Override
    public User findUserCode(String userCode){
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(userCode);
        List<User> userList = userMapper.selectByExample(example);
        if(userList.size()>0){
            return userList.get(0);
        }
        return null;

    }
    //修改用户信息
    @Override
    public int uplodUserById(User user)throws Exception{
        return userMapper.updateByPrimaryKeySelective(user);
    }
    //删除用户
    @Override
    public int deleteUserByid(Long l)throws Exception {
        return userMapper.deleteByPrimaryKey(l);
    }
    //综合查询
    @Override
    public PageInfo<User> selectUserListByYe(Integer pageNum, int pageSize, String queryname,Long queryUserRole)throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (queryname!=null&&queryname!=""){
            criteria.andUserNameLike("%"+queryname+"%");
        }
        if (queryUserRole!=null&&queryUserRole>0){
            criteria.andUserRoleEqualTo(queryUserRole);
        }
        List<User> userList = userMapper.selectByExample(example);
        if(userList!=null){
            for (User user : userList) {
                Role role = roleMapper.selectByPrimaryKey(user.getUserRole());
                user.setRoleName(role.getRoleName());
            }
        }
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }

    @Override
    public int selectUserPassword(User user) throws Exception {

        UserExample example = new UserExample();

        return userMapper.updateByPrimaryKeySelective(user);

    }
}
