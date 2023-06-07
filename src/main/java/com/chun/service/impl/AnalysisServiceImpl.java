package com.chun.service.impl;

import com.chun.dao.AnalysisMapper;
import com.chun.pojo.Analysis;
import com.chun.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisMapper analysisMapper;

    @Override
    public List<Analysis> typeBook() {
        return analysisMapper.typeBook();
    }
}
