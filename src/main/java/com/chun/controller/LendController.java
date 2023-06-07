package com.chun.controller;

import com.chun.pojo.BookInfo;
import com.chun.pojo.BookTypeInfo;
import com.chun.pojo.Lend;
import com.chun.pojo.ReaderInfo;
import com.chun.pojo.impl.BookVO;
import com.chun.pojo.impl.LendVO;
import com.chun.service.BookInfoService;
import com.chun.service.LendService;
import com.chun.service.ReaderInfoService;
import com.chun.utils.ServerResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/23/10:32
 * @Description:
 */
@Controller
public class LendController {
    @Autowired
    private LendService lendService;
    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private ReaderInfoService readerInfoService;

    //查询全部借阅信息
    @RequestMapping(value = "/SelectAllLendInfo")
    @ResponseBody
    public ServerResponse SelectAllLendInfo(Integer page, Integer limit, String name, String readerNumber,
                                            Integer backType, Integer status) {
        System.out.println("======================查询全部借阅信息==================");
        PageHelper.startPage(page, limit);
        List<Lend> lends = lendService.SelectAllLendInfo(name, readerNumber, backType, status);
        PageInfo<Lend> pageInfo = new PageInfo(lends);
        return ServerResponse.createBySuccess("查询全部借阅信息", pageInfo.getList(), pageInfo.getTotal());
    }

    //查询所有可借出 图书信息
    @RequestMapping(value = "/SelectOneBookInfoStatus")
    @ResponseBody
    public ServerResponse<List<BookInfo>> SelectOneBookInfoStatus(BookVO bookVO) {
        System.out.println("=======================查询所有可借出 图书信息================");
        PageHelper.startPage(bookVO.getPage(), bookVO.getLimit());
        List<BookInfo> bookInfos = lendService.SelectOneBookInfoStatus(null);
//        System.out.println(bookInfos);
        PageInfo<BookInfo> pageInfo = new PageInfo(bookInfos);
        return ServerResponse.createBySuccess("查询所有可借出 图书信息", pageInfo.getList(), pageInfo.getTotal());
    }

    //借阅书籍 修改书籍状态  LendBooksListStatus
    //修改图书状态
    @RequestMapping(value = "/LendBooksListStatus", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse LendBooksListStatus(String readerNumber, String ids) { //readerNumber
        System.out.println("============================这是单本书籍借阅=============================");
        System.out.println("==============readerNumber是===" + readerNumber + "=======================================");
        System.out.println("==============id是===" + ids + "=======================================");
        //获取读者id
        ReaderInfo readerInfo = readerInfoService.selectRIDwhereReaderNumber(readerNumber);
        int readerId = readerInfo.getId();
        //更新图书信息
        String[] splitarr = ids.split(",");
        int[] array = Arrays.stream(splitarr).mapToInt(Integer::parseInt).toArray();
        int reBook = lendService.updateArrBookSubmit2(splitarr);
        System.out.println("==============================reBook===" + reBook);
        //更新借阅信息
        List<Lend> lendList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            Lend lend = new Lend(array[i], readerId);
            lendList.add(lend);
        }
        int i1 = 0;
        if (reBook > 0) {
            int insertArrBookSubmit4Shu = lendService.insertArrBookSubmit4(lendList);
            System.out.println("========================insertArrBookSubmit4Shu===" + insertArrBookSubmit4Shu);
            if (insertArrBookSubmit4Shu > 0) {
                i1 = 1;
            }
        }
        if (i1 > 0) {
            return ServerResponse.createBySuccess("借阅成功", null);
        } else {
            return ServerResponse.createByError("借阅失败");
        }
    }

    /* 还书 backOne 获取借阅信息ids 然后进行
    1、修改图书状态: 用获取到的借阅信息的图书id 批量修改图书表的借阅状态
    2、更新借阅状态: 更新借阅表的状态（归还日期 归还类型 0为正常归还）
    */
    @RequestMapping(value = "/BackBooksByIds", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse BackBooksByIds(String ids) {
        String[] splitarr = ids.split(",");
        int i = lendService.BackBooksByIds(splitarr);
        if (i > 0) {
            return ServerResponse.createBySuccess("还书成功", null);
        } else {
            return ServerResponse.createByError("还书失败");
        }

    }

    /*  删除图书信息
    * */
    @RequestMapping(value = "/deleteLendInfoByIds", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse DeleteLend(String ids) {
        String[] splitarr = ids.split(",");
        int i = lendService.DeleteLend(splitarr);
        if (i > 0) {
            return ServerResponse.createBySuccess("删除成功", null);
        } else {
            return ServerResponse.createByError("删除失败");
        }

    }
    //异常还书

    @RequestMapping(value = "/excBackBookController", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse excBackBookController(Lend lend) {
        int i = lendService.excBackBook(lend);
        if (i > 0) {
            return ServerResponse.createBySuccess("删除成功", null);
        } else {
            return ServerResponse.createByError("删除失败");
        }

    }
}
