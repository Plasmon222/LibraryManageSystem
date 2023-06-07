package com.chun.service.impl;

import com.chun.dao.BookInfoMapper;
import com.chun.dao.LendInfoMapper;
import com.chun.dao.ReaderInfoMapper;
import com.chun.pojo.BookInfo;
import com.chun.pojo.Lend;
import com.chun.pojo.ReaderInfo;
import com.chun.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/23/10:34
 * @Description:
 */
@Service
public class LendServiceImpl implements LendService {
    @Autowired
    private LendInfoMapper lendInfoMapper;
    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Autowired
    private ReaderInfoMapper readerInfoMapper;

    @Override
    public List<Lend> SelectAllLendInfo(String name,String readerNumber,Integer backType,Integer status) {
        Lend lend=new Lend();
        BookInfo bookInfo=new BookInfo();
        ReaderInfo readerInfo=new ReaderInfo();
        if(name!=null&&name!=""){
            bookInfo.setName(name);
        }
        if (readerNumber!=null&&readerNumber!=""){
            readerInfo.setReaderNumber(readerNumber);
        }

        if (backType!=null){
            lend.setBackType(backType);
        }else {
            lend.setBackType(996);
        }


        if (status!=null){
            bookInfo.setStatus(status);
        }
        else {
            bookInfo.setStatus(996);
        }
        lend.setBookInfo(bookInfo);
        lend.setReaderInfo(readerInfo);
        return lendInfoMapper.SelectAllLendInfo(lend);
    }

    @Override
    public List<BookInfo> SelectOneBookInfoStatus(BookInfo bookInfo) {
        return lendInfoMapper.SelectOneBookInfoStatus(bookInfo);
    }

    @Override
    public int updateOneBookSubmit(int id) {
        return lendInfoMapper.updateOneBookSubmit(id);
    }

    @Override
    public int updateArrBookSubmit2(Object[] ids) {
        return lendInfoMapper.updateArrBookSubmit2(ids);
    }

    @Override
    public int insertArrBookSubmit3(int readerId, Object[] ids) {
        return lendInfoMapper.insertArrBookSubmit3(readerId,ids);
    }

    @Override
    public int insertArrBookSubmit4(List<Lend> lendList) {
        return lendInfoMapper.insertArrBookSubmit4(lendList);
    }


    @Override
    public int BackBooksByIds(String[] splitarr) {
        List<Lend> lendList =new ArrayList<>();
        //获取选中借阅信息列表
        for (int i = 0; i <splitarr.length ; i++) {
            Lend lend = lendInfoMapper.SelectLendDateByLID(splitarr[i]);
            lendList.add(lend);
        }
        //更新图书信息
        int upbook=0;
        for (int i = 0; i <lendList.size() ; i++) {
            upbook += lendInfoMapper.UpdateBookStatusByBID(lendList.get(i).getBookId());
        }
        //更新借阅信息
        int uplend=0;
        for (int i = 0; i < lendList.size(); i++) {
            uplend += lendInfoMapper.UpdateBackBookLend(lendList.get(i).getId());
        }
        return uplend;
    }

    @Override
    public int DeleteLend(Object[] ids) {
        int del=0;
        for (int i = 0; i < ids.length; i++) {
            del += lendInfoMapper.DeleteLend((String) ids[i]);
        }
        return del;
    }

    @Override
    public int excBackBook(Lend lend) {
        return lendInfoMapper.excBackBook(lend);
    }


}
