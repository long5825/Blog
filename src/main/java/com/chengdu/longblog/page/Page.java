package com.chengdu.longblog.page;

import java.util.List;

public class Page<T> {
    private Integer page = 1;//当前页数
    private Integer limit;//每页显示数
    private Integer totalPage;//总页数
    private Integer total;//总记录数
    private List<T> pageRecode;//当前页面的数据集合

    //是否存在上一页


    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", limit=" + limit +
                ", totalPage=" + totalPage +
                ", total=" + total +
                ", pageRecode=" + pageRecode +
                '}';
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getPageRecode() {
        return pageRecode;
    }

    public void setPageRecode(List<T> pageRecode) {
        this.pageRecode = pageRecode;
    }
}
