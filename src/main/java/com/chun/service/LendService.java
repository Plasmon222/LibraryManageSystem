package com.chun.service;

import com.chun.pojo.BookInfo;
import com.chun.pojo.Lend;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/23/10:34
 * @Description:
 */
public interface LendService {

    public List<Lend> SelectAllLendInfo(String name,String readerNumber,Integer backType,Integer status);
    public List<BookInfo> SelectOneBookInfoStatus(BookInfo bookInfo);

    //    updateOneBookSubmit
    public int updateOneBookSubmit(int id);

    public int updateArrBookSubmit2(Object[] ids);
    public int insertArrBookSubmit3(@Param("readerId")int readerId, Object[] ids);//批量arr更新借阅信息3
    public int insertArrBookSubmit4(List<Lend> lendList);//批量list更新借阅信息4

    public  int BackBooksByIds(String[] splitarr);

    public int DeleteLend(Object[] ids);

    public int excBackBook(Lend lend);

}
