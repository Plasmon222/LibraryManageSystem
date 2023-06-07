package com.chun.service.impl;

import com.chun.dao.BookInfoMapper;
import com.chun.pojo.BookInfo;
import com.chun.pojo.BookTypeInfo;
import com.chun.service.BookInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/16/15:15
 * @Description:
 */
@Service
public class BookInfoServiceImpl implements BookInfoService {
    @Autowired
    private BookInfoMapper BIM;

    public List<BookInfo> SelectAllBookInfo(BookInfo bookInfo) {
        return BIM.SelectAllBookInfo(bookInfo);
    }

    @Override
    public List<BookTypeInfo> SelectAllBookTypeInfo(BookTypeInfo bookTypeInfo) {
        return BIM.SelectAllBookTypeInfo(bookTypeInfo);
    }

    @Override
    public BookInfo SelectOneBookInfo(int id) {
        return BIM.SelectOneBookInfo(id);
    }

    @Override
    public int AddOneBook(BookInfo bookInfo) {
        return BIM.AddOneBook(bookInfo);
    }

    @Override
    public int deleteBook(int id) {
        return BIM.deleteBook(id);
    }

    @Override
    public int updateBookSubmit(BookInfo bookInfo) {
        return BIM.updateBookSubmit(bookInfo);
    }

    @Override
    public int addTypeSubmit(BookTypeInfo bookTypeInfo) {
        return BIM.addTypeSubmit(bookTypeInfo);
    }

    @Override
    public int deleteType(int id) {
        return BIM.deleteType(id);
    }

    @Override
    public int updateTypeSubmit(BookTypeInfo bookTypeInfo) {
        return BIM.updateTypeSubmit(bookTypeInfo);
    }

    @Override
    public BookTypeInfo SelectOneBookTypeInfo(int id) {
        return BIM.SelectOneBookTypeInfo(id);
    }


}
