package com.chun.pojo.impl;

import com.chun.pojo.Notice;

/**
 * @Auther:Plasmon222
 * @Date: 2023/6/1/10:30
 * @Description:
 */
public class NoticeVO extends Notice {
    private int page;// publish_date每页显示数量
    private int limit;
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }

}
