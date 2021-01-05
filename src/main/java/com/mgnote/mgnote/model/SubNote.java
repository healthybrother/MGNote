package com.mgnote.mgnote.model;

import com.mgnote.mgnote.model.dto.BriefNote;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "note")
public class SubNote extends AbstractNote{
    private List<BriefNote> subNoteList;

    public SubNote() {
    }

    public SubNote(List<BriefNote> subNoteList) {
        this.subNoteList = subNoteList;
    }

    public SubNote(String id, String name, String content, Date createTime, Date updateTime, boolean del, List<BriefNote> subNoteList) {
        super(id, name, content, createTime, updateTime, del);
        this.subNoteList = subNoteList;
    }

    public List<BriefNote> getSubNoteList() {
        return subNoteList;
    }

    public void setSubNoteList(List<BriefNote> subNoteList) {
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
