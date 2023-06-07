package com.chun.dao;

import com.chun.pojo.BookInfo;
import com.chun.pojo.BookTypeInfo;
import com.chun.pojo.Lend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/16/15:16
 * @Description:
 */
public interface LendInfoMapper {
    /**
     * 借阅信息查询
     */
    public List<Lend> SelectAllLendInfo(Lend lend);


    public List<BookInfo> SelectOneBookInfoStatus(BookInfo bookInfo);


    public int updateOneBookSubmit(int id); //updateOneBookSubmit

    public int updateArrBookSubmit2(Object[] ids); //批量arr更新图书信息2
    public int insertArrBookSubmit3(@Param("readerId")int readerId,Object[] ids);//批量arr更新借阅信息3(嘎嘎报错)
    public int insertArrBookSubmit4(List<Lend> lendList);//批量list更新借阅信息4

    public Lend SelectLendDateByLID(String id);
    public int UpdateBookStatusByBID(int id);

    public int UpdateBackBookLend(int id);

    public int DeleteLend(String id);

    public int excBackBook(Lend lend);
}
