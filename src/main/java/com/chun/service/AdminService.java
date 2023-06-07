package com.chun.service;


import com.chun.pojo.Admin;

import java.util.List;

public interface AdminService {


    /**
     * 根据用户名和密码查询用户信息
     */
    Admin queryUserByNameAndPassword(String username, String password);
//    Admin updatePwdSubmit2(String );

    //修改管理员密码
    int updatePwdSubmit111(Admin admin);

    List<Admin> selectAllAdmin();
    Admin selectAdminById(String id);
    int addAdmin(Admin admin); //添加管理员
    int delAdmin(String ids);//删除管理员
    int upAdminPassword(Admin admin);//更新密码
}
