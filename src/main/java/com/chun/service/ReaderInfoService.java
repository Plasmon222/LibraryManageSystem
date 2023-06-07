package com.chun.service;


import com.chun.pojo.ReaderInfo;

import java.util.List;

public interface ReaderInfoService {


    /**
     * 根据用户名和密码查询用户信息
     */
    ReaderInfo queryUserInfoByNameAndPassword(String username, String password);

    List<ReaderInfo> readerAll(ReaderInfo readerInfo);
    ReaderInfo readerOne(int id);
    ReaderInfo selectRIDwhereReaderNumber(String readerNumber);//根据读者卡号查读者

    int addReaderSubmit(ReaderInfo readerInfo);
    int deleteReader(int id);

    int updateReaderSubmit(ReaderInfo readerInfo);
}
