package com.chun.controller;

import com.chun.pojo.Admin;
import com.chun.service.AdminService;
import com.chun.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther:Plasmon222
 * @Date: 2023/6/1/18:49
 * @Description:
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("selectAllAdmin")
    @ResponseBody
    public ServerResponse selectAllAdmin(){
        List<Admin> admins = adminService.selectAllAdmin();
        return ServerResponse.createBySuccess("查询全部管理员",admins,admins.size());
    }
    @RequestMapping("addAdmin")
    @ResponseBody
    public ServerResponse addAdmin(Admin admin){ //添加管理员
        int i = adminService.addAdmin(admin);
        return ServerResponse.createBySuccess("添加管理员",null);
    }

    @RequestMapping("delAdmin")
    @ResponseBody
    public ServerResponse delAdmin(String ids){ //删除管理员
        int i = adminService.delAdmin(ids);
        if (i>0){
            return ServerResponse.createBySuccess("删除管理员",null);
        }else {
            return ServerResponse.createByError("删除管理员失败");
        }

    }

    @RequestMapping("upAdminPassword")
    @ResponseBody
    public ServerResponse upAdminPassword(Admin admin){ //更新管理员密码
        int i = adminService.upAdminPassword(admin);
        return ServerResponse.createBySuccess("更新管理员密码",null);
    }


    //修改密码
    @RequestMapping(value = "/updatePwdSubmit3")
    @ResponseBody
    public ServerResponse updatePwdSubmit3(String oldPwd, String newPwd, HttpSession session) {
        System.out.println("================ADMIN----PWD==============================");
        Admin admin = (Admin) session.getAttribute("upAdmin");
        System.out.println(admin);
        admin.setPassword(newPwd);
        int i = adminService.updatePwdSubmit111(admin);
//        if (i > 0) {
            return ServerResponse.createBySuccess("修改成功", null);
//        } else {
//            return ServerResponse.createByError("旧密码不正确");
//        }
    }
}
