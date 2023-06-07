package com.chun.dao;


import com.chun.pojo.ReaderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReaderInfoMapper {

    /**
     * 根据用户名和密码查询用户信息
     */
    ReaderInfo queryUserInfoByNameAndPassword(@Param("username") String username, @Param("password") String password);

    //    查询所有读者信息
    List<ReaderInfo> readerAll(ReaderInfo readerInfo);

    ReaderInfo readerOne(int id); //根据id查读者

    ReaderInfo selectRIDwhereReaderNumber(String readerNumber);//根据读者卡号查读者

    //    添加读者
    int addReaderSubmit(ReaderInfo readerInfo);

    //  删除读者
    int deleteReader(int id);

    //更新一个读者
    int updateReaderSubmit(ReaderInfo readerInfo);


}