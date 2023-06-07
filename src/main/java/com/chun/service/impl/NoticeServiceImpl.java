package com.chun.service.impl;

import com.chun.dao.NoticeMapper;
import com.chun.pojo.Notice;
import com.chun.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:Plasmon222
 * @Date: 2023/6/1/9:45
 * @Description:
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> selectAllNotice(Notice notice) {
        return noticeMapper.selectAllNotice(notice);
    }

    @Override
    public int addNotice(Notice notice) {
        return noticeMapper.addNotice(notice);
    }

    @Override
    public int delNotices(String ids) {
        String[] splitarr = ids.split(",");
        int del1=0;
        System.out.println("============================================spli111");

        for (int i = 0; i < splitarr.length; i++) {
            System.out.println(splitarr[i]);
            del1 += noticeMapper.delNotices(splitarr[i]);
        }
        return del1;
    }

    @Override
    public Notice updateNoticePage(String id) {
        return noticeMapper.updateNoticePage(id);
    }
}
