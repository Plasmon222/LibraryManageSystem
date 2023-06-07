package com.chun.controller;

import com.chun.pojo.*;
import com.chun.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BaseController {

    @Autowired
//    private NoticeService noticeService;
    private BookInfoService bookInfoService;
    @Autowired
    private ReaderInfoService readerInfoService;
    @Autowired
    private LendService lendService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private AdminService adminService;
    @Autowired AnalysisService analysisService;
    /**
     * 首页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }


    /**
     * 欢迎页面跳转
     * @return
     */
    @GetMapping("/welcome")
    public String welcome(Model model){
        return "welcome";
    }

    @GetMapping("/updatePassword")
    public String updatePwd(){
        return "pwdUpdate/updatePwd";
    }

    //图书信息修改跳转
    @GetMapping("/queryBookInfoById")
    public String queryBookInfoById(int id,Model model){
        BookInfo bookInfo = bookInfoService.SelectOneBookInfo(id);
        System.out.println(" ============== 这是视图解析器===>queryBookInfoById: bookInfo ==============");
        System.out.println(bookInfo);
        model.addAttribute("info",bookInfo);
        return "/book/updateBook";
    }

    //跳转到读者模块
    @GetMapping("/readerIndex")
    public  String readerIndex(){
        return "reader/readerIndex";
    }
    @GetMapping("/readerAdd")
    public String readerAdd(){
        return "reader/readerAdd";
    }
    @GetMapping("/queryReaderInfoById")
    public String queryReaderInfoById(int id, HttpServletRequest request){
        System.out.println("==================视图解析===================");
        System.out.println(id);
        ReaderInfo readerInfo = readerInfoService.readerOne(id);
        System.out.println(readerInfo);
        request.getSession().setAttribute("info",readerInfo);
        return "reader/updateReader";
    }

    //分类页面跳转
    @GetMapping("/typeIndex")
    public String typeIndex(){
        return "type/typeIndex";
    }
    @GetMapping("/typeAdd")
    public String typeAdd(){
        return "type/typeAdd";
    }

    @GetMapping("/queryTypeInfoById")
    public String queryTypeInfoById(int id, HttpServletRequest request){
        System.out.println("==================视图解析===================");
        System.out.println(id);
        ReaderInfo readerInfo = readerInfoService.readerOne(id);
        BookTypeInfo bookTypeInfo = bookInfoService.SelectOneBookTypeInfo(id);
        System.out.println(bookTypeInfo);
        request.getSession().setAttribute("info",bookTypeInfo);
        return "type/updateType";
    }


    //借阅功能跳转
    @GetMapping("/lendListIndex")
    public String lendListIndex(){
        return "/lend/lendListIndex";
    }

    //借阅添加功能

    @GetMapping("/addLendList")
    public String addLendList(){
        return "/lend/addLendList";
    }
    //异常还书归还页面跳转
    @GetMapping("/excBackBook")
    public String excBackBook(String id,HttpServletRequest request){
        System.out.println("==================视图解析===================");
        System.out.println(id);
//        ReaderInfo readerInfo = readerInfoService.readerOne(id);
//        lendService.SelectLendDateByLID();
//        System.out.println(readerInfo);
        request.getSession().setAttribute("lendid",id);

        return "/lend/excBackBook";
    }

    //查看借阅书籍时间线列表
    @GetMapping("/lookBookList")
    public String lookBookList(){
        return "/lend/lookBookList";
    }
    //公告管理---------------------
    //公告主页
    @GetMapping("/noticeIndexOfBack")
    public String noticeIndexOfBack(){
        return "/notice/noticeIndexOfBack";
    }

    @GetMapping("/addNoticepage")
    public String addNotice(){
        return "/notice/noticeAdd";
    }


    @GetMapping("/updateNoticePage")
    public String updateNoticePage(String id,HttpServletRequest request){
        System.out.println(id);
        Notice notice = noticeService.updateNoticePage(id);
        request.getSession().setAttribute("notice",notice);
        return "/notice/updateNotice";
    }
    //管理员模块
    //管理员主页
    @GetMapping("/adminIndex")
    public String adminIndex(){
        return "/admin/adminIndex";
    }


    @GetMapping("/addAdminPage")
    public String addAdminPage(){
        return "/admin/adminAdd";
    }

    @GetMapping("/updateAdminPage")
    public String updateAdminPage(String id,HttpServletRequest request){
        Admin admin = adminService.selectAdminById(id);
        request.getSession().setAttribute("upAdmin",admin);
        return "/admin/updateAdmin";
    }
    /**
     * 统计分析
     */
    @GetMapping("/analysis")
    public String analysis(Model model){
        List<Analysis> list =analysisService.typeBook();
        model.addAttribute("list",list);
        return "statistic/analysis";
    }



}
