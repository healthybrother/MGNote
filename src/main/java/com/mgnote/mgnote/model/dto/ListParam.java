package com.mgnote.mgnote.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListParam {

    @Min(0)
    private Integer currentPage;

    @Max(50)
    private Integer pageSize;

    private List<Order> orderBy;

    private Long totalNum;
    private Integer totalPageNum;

    public ListParam(@Min(1) Integer currentPage, @Max(50) Integer pageSize, List<Order> orderBy, Long totalNum, Integer totalPageNum) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.totalNum = totalNum;
        this.totalPageNum = totalPageNum;
    }

    public ListParam() {

    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Order> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(List<Order> orderBy) {
        this.orderBy = orderBy;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    @Override
    public String toString() {
        return "ListParam{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", orderBy=" + orderBy +
                ", totalNum=" + totalNum +
                ", totalPageNum=" + totalPageNum +
                '}';
    }
}
