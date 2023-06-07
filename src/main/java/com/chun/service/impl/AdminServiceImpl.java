package com.chun.service.impl;


import com.chun.dao.AdminMapper;
import com.chun.pojo.Admin;
import com.chun.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public Admin queryUserByNameAndPassword(String username, String password) {
        return adminMapper.queryUserByNameAndPassword(username,password);
    }

    @Override
    public int updatePwdSubmit111(Admin admin) {
        return adminMapper.updatePwdSubmit111(admin);
    }

    @Override
    public List<Admin> selectAllAdmin() {
        return adminMapper.selectAllAdmin();
    }

    @Override
    public Admin selectAdminById(String id) {
        return adminMapper.selectAdminById(id);
    }

    @Override
    public int addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin);
    }

    @Override
    public int delAdmin(String ids) {
        String[] splitarr = ids.split(",");
        int delshu=0;
        for (int i = 0; i < splitarr.length; i++) {
            delshu += adminMapper.delAdmin(splitarr[i]);
        }

        return delshu;
    }

    @Override
    public int upAdminPassword(Admin admin) {
        return adminMapper.upAdminPassword(admin);
    }
}
