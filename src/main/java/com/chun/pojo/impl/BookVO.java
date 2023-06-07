package com.chun.pojo.impl;

import com.chun.pojo.BookInfo;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/25/17:59
 * @Description:
 */
public class BookVO extends BookInfo {
    private int page;// 每页显示数量
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
