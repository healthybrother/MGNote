package com.mgnote.mgnote.model;

import java.util.Date;
import java.util.List;

public class SubNote extends AbstractNote{
    private List<SubNote> subNoteList;

    public SubNote() {
    }

    public SubNote(String id, String name, String content, Date createTime, Date updateTime, boolean del, List<SubNote> subNoteList) {
        super(id, name, content, createTime, updateTime, del);
        this.subNoteList = subNoteList;
    }

    public List<SubNote> getSubNoteList() {
        return subNoteList;
    }

    public void setSubNoteList(List<SubNote> subNoteList) {
        this.subNoteList = subNoteList;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", content='" + getContent() + '\'' +
                ", createTime=" + getCreateTime() +
                ", updateTime=" + getUpdateTime() +
                ", subNoteList=" + subNoteList +
                '}';
    }
}
