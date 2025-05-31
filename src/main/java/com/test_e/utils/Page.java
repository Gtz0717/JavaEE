package com.test_e.utils;

import java.util.List;
public class Page<T> {
    private int currentPageNo;    // 当前页码
    private int pageSize;        // 每页显示记录数
    private int totalCount;      // 总记录数
    private int totalPageCount;  // 总页数
    private int upPageNo;        // 上一页页码
    private int nextPageNo;      // 下一页页码
    private List<T> items;       // 当前页的数据列表

    public Page() {
    }

    public Page(int currentPageNo, int pageSize, int totalCount, List<T> items) {
        this.currentPageNo = currentPageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.items = items;

        // 计算总页数
        this.totalPageCount = (totalCount + pageSize - 1) / pageSize;

        // 计算上一页
        this.upPageNo = currentPageNo > 1 ? currentPageNo - 1 : 1;

        // 计算下一页
        this.nextPageNo = currentPageNo < totalPageCount ? currentPageNo + 1 : totalPageCount;
    }

    // Getter 和 Setter 方法
    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        // 当设置总记录数时，重新计算总页数
        this.totalPageCount = (totalCount + pageSize - 1) / pageSize;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getUpPageNo() {
        return upPageNo;
    }

    public void setUpPageNo(int upPageNo) {
        this.upPageNo = upPageNo;
    }

    public int getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    // 判断是否有上一页
    public boolean isHasPrevious() {
        return currentPageNo > 1;
    }

    // 判断是否有下一页
    public boolean isHasNext() {
        return currentPageNo < totalPageCount;
    }
}