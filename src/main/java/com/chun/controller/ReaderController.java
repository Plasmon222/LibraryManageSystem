package com.chun.controller;

import com.chun.pojo.BookInfo;
import com.chun.pojo.ReaderInfo;
import com.chun.service.ReaderInfoService;
import com.chun.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/21/14:24
 * @Description:
 */

//@Controller
@RestController
public class ReaderController {
    @Autowired
    private ReaderInfoService readerService;

    //查询全部读者信息
//    @RequestMapping(value = "/readerAll",method = RequestMethod.POST)
    @GetMapping(value = "/readerAll")
    @ResponseBody
    public ServerResponse readerAll(ReaderInfo readerInfo) {
        System.out.println("=====================查询所有读者信息=============================");
        List<ReaderInfo> readerInfos = readerService.readerAll(readerInfo);
        return ServerResponse.createBySuccess("查询成功", readerInfos, readerInfos.size());
    }

    //  添加读者
    @RequestMapping(value = "/addReaderSubmit", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addReaderSubmit(ReaderInfo readerInfo) {
        System.out.println("==============添加用户====================");
        System.out.println(readerInfo);
//        Date date = new Date();
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//        System.out.println("===============date");
//        System.out.println(date);
//        readerInfo.setRegisterDate(date);
//        System.out.println("=======================readINfo");
//        System.out.println(dateFormat.format(date));
//        System.out.println(readerInfo);

        int i = readerService.addReaderSubmit(readerInfo);
        if (i > 0) {
            return ServerResponse.createBySuccess("添加成功", null);
        } else {
            return ServerResponse.createByError("添加失败");
        }
    }

    //  删除读者
    @RequestMapping(value = "/deleteReader", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteReader(String ids) {
        System.out.println("==============删除用户====================");
        int i1 = 0;
        String[] splitarr = ids.split(",");
        for (int i = 0; i < splitarr.length; i++) {
            Integer intId = Integer.valueOf(splitarr[i]);
            int i2 = readerService.deleteReader(intId);
            i1 = i2;
        }
        if (i1 > 0) {
            return ServerResponse.createBySuccess("删除成功", null);
        } else {
            return ServerResponse.createByError("删除失败");
        }
    }
    //更新一个读者
    @RequestMapping(value = "/updateReaderSubmit",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateBookSubmit(ReaderInfo readerInfo){
        System.out.println("============================这是读者信息更改=============================");
        int i = readerService.updateReaderSubmit(readerInfo);
        if(i>0){
            return ServerResponse.createBySuccess("添加成功",null);
        }else {
            return ServerResponse.createByError("添加失败");
        }
    }

}
