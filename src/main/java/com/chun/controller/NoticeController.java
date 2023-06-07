package com.chun.controller;

import com.chun.pojo.Notice;
import com.chun.service.NoticeService;
import com.chun.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther:Plasmon222
 * @Date: 2023/6/1/9:44
 * @Description:
 */
@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/selectAllNotice")
    @ResponseBody
    public ServerResponse selectAllNotice(Notice notice){
        List<Notice> notices = noticeService.selectAllNotice(notice);
        System.out.println("====================这是notices=============================");
        System.out.println(notices);
        return ServerResponse.createBySuccess("查询全部公告信息",notices,notices.size());
    }

    @RequestMapping(value = "/addNotice")
    @ResponseBody
    public ServerResponse addNotice(Notice notice){
        int i = noticeService.addNotice(notice);
        if (i>0){
            return ServerResponse.createBySuccess("创建成功",null);
        }else{
            return ServerResponse.createByError("创建失败");
        }
    }

    @RequestMapping(value = "/delNotices")
    @ResponseBody
    public ServerResponse delNotices(String ids){
        int i = noticeService.delNotices(ids);
        if (i>0){
            return ServerResponse.createBySuccess("删除成功",null);
        }else{
            return ServerResponse.createByError("删除失败");
        }
    }

}
