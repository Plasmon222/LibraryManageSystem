package com.chun.service;

import com.chun.pojo.BookInfo;
import com.chun.pojo.BookTypeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/16/15:15
 * @Description:
 */
public interface BookInfoService {
    //查询所有图书信息
    public List<BookInfo> SelectAllBookInfo(BookInfo bookInfo);
    //查询所有分类
    public List<BookTypeInfo> SelectAllBookTypeInfo(BookTypeInfo bookTypeInfo);

    public BookInfo SelectOneBookInfo(int id);

    public int AddOneBook(BookInfo bookInfo);

    public int deleteBook(int id);

    public int updateBookSubmit(BookInfo bookInfo);

    public int addTypeSubmit(BookTypeInfo bookTypeInfo);
    public int deleteType(int id);

    public int updateTypeSubmit(BookTypeInfo bookTypeInfo);

    public BookTypeInfo SelectOneBookTypeInfo(int id);

}
