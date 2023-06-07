package com.chun.pojo.impl;

import com.chun.pojo.Lend;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/25/18:18
 * @Description:
 */
public class LendVO extends Lend {
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
