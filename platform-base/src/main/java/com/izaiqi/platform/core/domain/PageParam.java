package com.izaiqi.platform.core.domain;

import com.izaiqi.platform.core.common.Const;

/**
 * 返回分页参数基类
 *
 * @author deng.qiming
 * @date 2016年11月8日 上午10:49:47
 */
public class PageParam {
    private Integer pageIndex;
    private Integer pageSize;

    public PageParam() {
        this.pageIndex = Const.PAGE_PAGEINDEX_DEFAULT;
        this.pageSize = Const.PAGE_PAGESIZE_DEFAULT;
    }


    public PageParam(Integer pageIndex) {
        this.pageIndex = pageIndex;
        this.pageSize = Const.PAGE_PAGESIZE_DEFAULT;
    }


    public PageParam(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
