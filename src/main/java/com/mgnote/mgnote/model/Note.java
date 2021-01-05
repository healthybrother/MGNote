package com.mgnote.mgnote.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "note")
public class Note extends AbstractNote{
    private List<String> subNoteList;
    private static final Note newNote;

    static {
        newNote = new Note();
        newNote.setDel(false);
        newNote.setCreateTime(new Date());
        newNote.setUpdateTime(new Date());
    }

    public Note() {
    }

    public Note(List<String> subNoteList) {
        this.subNoteList = subNoteList;
    }

    public Note(String id, String name, String content, Date createTime, Date updateTime, boolean del, List<String> subNoteList) {
        super(id, name, content, createTime, updateTime, del);
        this.subNoteList = subNoteList;
    }

    public static Note getNewNote(){return newNote;}

    public List<String> getSubNoteList() {
        return subNoteList;
    }

    public void setSubNoteList(List<String> subNoteList) {
        this.subNoteList = subNoteList;
    }

    public void init(){
        setDel(false);
        setCreateTime(new Date());
        setUpdateTime(new Date());
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
