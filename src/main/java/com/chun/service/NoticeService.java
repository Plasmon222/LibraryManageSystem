package com.chun.service;

import com.chun.pojo.Notice;

import java.util.List;

/**
 * @Auther:Plasmon222
 * @Date: 2023/6/1/9:45
 * @Description:
 */
public interface NoticeService {
    public List<Notice> selectAllNotice(Notice notice);

    public int addNotice(Notice notice);
    public int delNotices(String id);

    public Notice updateNoticePage(String id);

}
