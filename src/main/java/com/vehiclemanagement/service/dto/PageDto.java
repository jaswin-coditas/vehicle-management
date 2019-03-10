package com.vehiclemanagement.service.dto;

import java.io.Serializable;
import java.util.List;

public class PageDto<T> implements Serializable {

    private Long totalCount;

    private List<T>  currentPageData;

    private Integer perPage;

    private Integer currentPageNo;

    private Integer nextPageNo;

    private Integer prevPageNo;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getCurrentPageData() {
        return currentPageData;
    }

    public void setCurrentPageData(List<T> currentPageData) {
        this.currentPageData = currentPageData;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public Integer getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(Integer nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public Integer getPrevPageNo() {
        return prevPageNo;
    }

    public void setPrevPageNo(Integer prevPageNo) {
        this.prevPageNo = prevPageNo;
    }
}
