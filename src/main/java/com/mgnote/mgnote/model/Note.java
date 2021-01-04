package com.mgnote.mgnote.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Note")
public class Note extends AbstractNote{
    private List<SubNote> subNoteList;

    public Note() {
    }

    public Note(String id, String name, String content, Date createTime, boolean del, Date updateTime, List<SubNote> subNoteList) {
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
