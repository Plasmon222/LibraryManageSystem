package com.chun.controller;

import com.chun.pojo.BookInfo;
import com.chun.pojo.BookTypeInfo;
import com.chun.pojo.Lend;
import com.chun.pojo.impl.BookVO;
import com.chun.service.BookInfoService;
import com.chun.utils.ServerResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.chun.pojo.impl.TypeVo;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BookInfoController {
    @Autowired
    private BookInfoService bookInfoService;


    /**
     * 图书管理首页
     * @return
     */
    @GetMapping("/bookIndex")
    public String bookIndex(HttpServletRequest hr){
        return "book/bookIndex";
    }

    //查询分类
    @GetMapping("/findAllList")
    @ResponseBody
    public ServerResponse<List<BookTypeInfo>> bookType(){
        List<BookTypeInfo> bookTypeInfos = bookInfoService.SelectAllBookTypeInfo(null);
//        System.out.println("这是发现所有分类列表");
        return ServerResponse.createBySuccess("返回分类信息",bookTypeInfos,bookTypeInfos.size());
    }


    @GetMapping("/bookAll")
    @ResponseBody
    public ServerResponse<List<BookInfo>> bookAll(BookVO bookVO){
        System.out.println("=======================查询所有图书信息================");
        PageHelper.startPage(bookVO.getPage(),bookVO.getLimit());
        List<BookInfo> bookInfos = bookInfoService.SelectAllBookInfo(bookVO);
        System.out.println(bookInfos);
        PageInfo<BookInfo> pageInfo=new PageInfo (bookInfos);
        return ServerResponse.createBySuccess("查询全部借阅信息",pageInfo.getList(),pageInfo.getTotal());
    }



    //添加一本图书
    @RequestMapping(value = "/addBookSubmit",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse AddOneBook(BookInfo bookInfo){
        System.out.println("============================这是单本书籍添加=============================");
        System.out.println(bookInfo);
        int i = bookInfoService.AddOneBook(bookInfo);
        if(i>0){
            return ServerResponse.createBySuccess("添加成功",null);
        }else {
            return ServerResponse.createByError("添加失败");
        }
    }

    //删除一本图书
    @RequestMapping(value = "/deleteBook",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteBook(String ids){
        System.out.println("============================这是单本书籍删除=============================");
        System.out.println("==============id是==="+ids+"=======================================");
//        int i = bookInfoService.deleteBook(ids);
        int i1=0;
        String[] splitarr = ids.split(",");
        for (int i = 0; i < splitarr.length; i++) {
            Integer intId=Integer.valueOf(splitarr[i]);
            int i2 = bookInfoService.deleteBook(intId);
            i1=i2;
        }
        if(i1>0){
            return ServerResponse.createBySuccess("删除成功",null);
        }else {
            return ServerResponse.createByError("删除失败");
        }
    }


    //更新一本书
    @RequestMapping(value = "/updateBookSubmit/{UpBookId}",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateBookSubmit(BookInfo bookInfo,@PathVariable("UpBookId") int UpBookId){
        System.out.println("============================这是单本书籍更改=============================");
        bookInfo.setId(UpBookId);
        System.out.println(UpBookId);
        int i = bookInfoService.updateBookSubmit(bookInfo);
        if(i>0){
            return ServerResponse.createBySuccess("添加成功",null);
        }else {
            return ServerResponse.createByError("添加失败");
        }
    }


    //---------------图书分类—-----------
    @GetMapping(value = "/typeAll")
    @ResponseBody
    public ServerResponse typeAll(TypeVo TypeVo){
        System.out.println("============图书分类页面===========分类信息");
        PageHelper.startPage(TypeVo.getPage(),TypeVo.getLimit());
        List<BookTypeInfo> bookTypeInfos = bookInfoService.SelectAllBookTypeInfo(TypeVo);
        PageInfo<BookTypeInfo> pageInfo=new PageInfo<BookTypeInfo>(bookTypeInfos);
//        return ServerResponse.createBySuccess("查询全部图书信息",bookTypeInfos,bookTypeInfos.size());
        return ServerResponse.createBySuccess("查询全部图书信息",pageInfo.getList(),pageInfo.getTotal());
    }
    @RequestMapping(value = "/addTypeSubmit",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addTypeSubmit(BookTypeInfo bookTypeInfo){
        System.out.println("============================这是单本书籍添加=============================");
        System.out.println(bookTypeInfo);
        int i = bookInfoService.addTypeSubmit(bookTypeInfo);
        if(i>0){
            return ServerResponse.createBySuccess("添加成功",null);
        }else {
            return ServerResponse.createByError("添加失败");
        }
    }
    //删除分类
    @RequestMapping(value = "/deleteType",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteType(String ids){
        System.out.println("============================这是单本书籍删除=============================");
        System.out.println("==============id是==="+ids+"=======================================");
        int i1=0;
        String[] splitarr = ids.split(",");
        for (int i = 0; i < splitarr.length; i++) {
            Integer intId=Integer.valueOf(splitarr[i]);
            int i2 = bookInfoService.deleteType(intId);
            i1=i2;
        }
        if(i1>0){
            return ServerResponse.createBySuccess("删除成功",null);
        }else {
            return ServerResponse.createByError("删除失败");
        }
    }
    //修改分类

    @RequestMapping(value = "/updateTypeSubmit",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateTypeSubmit(BookTypeInfo bookTypeInfo){
        System.out.println("============================这是读者信息更改=============================");
        int i = bookInfoService.updateTypeSubmit(bookTypeInfo);
        if(i>0){
            return ServerResponse.createBySuccess("添加成功",null);
        }else {
            return ServerResponse.createByError("添加失败");
        }
    }


}
