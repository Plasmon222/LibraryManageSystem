package com.chun.dao;


import com.chun.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    /**
     * 根据用户名和密码查询用户信息
     */
    Admin queryUserByNameAndPassword(@Param("username") String username, @Param("password") String password);

    //修改管理员密码
    int updatePwdSubmit111(Admin admin);

    List<Admin> selectAllAdmin(); //查询全部管理员
    Admin selectAdminById(String id);//查询一个管理员
    int addAdmin(Admin admin); //添加管理员
    int delAdmin(String id);//删除管理员
    int upAdminPassword(Admin admin);//更新密码
}