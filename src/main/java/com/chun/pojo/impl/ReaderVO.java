package com.chun.pojo.impl;

import com.chun.pojo.ReaderInfo;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/25/18:01
 * @Description:
 */
public class ReaderVO extends ReaderInfo {
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
