package com.chun.pojo.impl;

import com.chun.pojo.BookTypeInfo;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/23/15:38
 * @Description:
 */
public class TypeVo extends BookTypeInfo {
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
