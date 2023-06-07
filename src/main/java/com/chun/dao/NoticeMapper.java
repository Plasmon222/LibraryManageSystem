package com.chun.dao;

import com.chun.pojo.Notice;

import java.util.List;

/**
 * @Auther:Plasmon222
 * @Date: 2023/6/1/9:46
 * @Description:
 */
public interface NoticeMapper {
    public List<Notice> selectAllNotice(Notice notice);
    public int addNotice(Notice notice);
    public int delNotices(String id);

    public Notice updateNoticePage(String id);

}
