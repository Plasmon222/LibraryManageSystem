package com.chun.dao;

import com.chun.pojo.BookInfo;
import com.chun.pojo.BookTypeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/16/15:16
 * @Description:
 */
public interface BookInfoMapper {
    /**
     * 图书信息查询
     */
    public List<BookInfo> SelectAllBookInfo(BookInfo bookInfo);
    public List<BookTypeInfo> SelectAllBookTypeInfo(BookTypeInfo bookTypeInfo);

    public BookInfo SelectOneBookInfo(int id);

    public int AddOneBook(BookInfo bookInfo);

    public int deleteBook(int id);

    public int updateBookSubmit(BookInfo bookInfo);
//    public int updateBookSubmit(BookInfo bookInfo,@Param("aaa") int UpBookId);

    public int addTypeSubmit(BookTypeInfo bookTypeInfo);
    public int deleteType(int id);

    public int updateTypeSubmit(BookTypeInfo bookTypeInfo);
    public BookTypeInfo SelectOneBookTypeInfo(int id);



}
