package com.mgnote.mgnote.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class AbstractNote {
    @Id
    private String id;
    private String name;
    private String content;
    private Date createTime;
    private Date updateTime;
    private boolean del;

    public AbstractNote() {
    }

    public AbstractNote(String id, String name, String content, Date createTime, Date updateTime, boolean del) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.del = del;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }
}
