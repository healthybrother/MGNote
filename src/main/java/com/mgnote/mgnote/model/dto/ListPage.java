package com.mgnote.mgnote.model.dto;

import com.mgnote.mgnote.util.ListUtil;
import org.springframework.data.domain.Page;

import java.util.List;

public class ListPage<Type> {
    private List<Type> list;
    private ListParam param;

    public ListPage(List<Type> list, ListParam param) {
        this.list = list;
        this.param = param;
    }

    public ListPage(Page<Type> page) {
        this.list = page.toList();
        this.param = ListUtil.getListParamByPage(page);
    }
    public List<Type> getList() {
        return list;
    }

    public void setList(List<Type> list) {
        this.list = list;
    }

    public ListParam getParam() {
        return param;
    }

    public void setParam(ListParam param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "ListPage{" +
                "list=" + list +
                ", param=" + param +
                '}';
    }
}
