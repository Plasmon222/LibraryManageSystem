package com.chun.service.impl;


import com.chun.dao.ReaderInfoMapper;
import com.chun.pojo.ReaderInfo;
import com.chun.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("readerInfoService")
public class ReaderInfoServiceImpl implements ReaderInfoService {

    @Autowired
    private ReaderInfoMapper readerInfoMapper;

    @Override
    public ReaderInfo queryUserInfoByNameAndPassword(String username, String password) {
        return readerInfoMapper.queryUserInfoByNameAndPassword(username, password);
    }

    @Override
    public List<ReaderInfo> readerAll(ReaderInfo readerInfo) {
        return readerInfoMapper.readerAll(readerInfo);
    }

    @Override
    public int addReaderSubmit(ReaderInfo readerInfo) {
        return readerInfoMapper.addReaderSubmit(readerInfo);
    }

    @Override
    public int updateReaderSubmit(ReaderInfo readerInfo) {
        return readerInfoMapper.updateReaderSubmit(readerInfo);
    }

    @Override
    public ReaderInfo selectRIDwhereReaderNumber(String readerNumber) {
        return readerInfoMapper.selectRIDwhereReaderNumber(readerNumber);
    }

    @Override
    public ReaderInfo readerOne(int id) {
        return readerInfoMapper.readerOne(id);
    }

    @Override
    public int deleteReader(int id) {
        return readerInfoMapper.deleteReader(id);
    }
}
