package com.mgnote.mgnote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "directory")
public class Directory{
    @Id
    private String id;
    private String name;
    private boolean del;
    private List<BriefDirectory> directoryList;
    private List<BriefDirectory> noteBookList;

    public Directory(){}

    public Directory(String id, String name, boolean del, List<BriefDirectory> directoryList, List<BriefDirectory> noteBookList) {
        this.id = id;
        this.name = name;
        this.del = del;
        this.directoryList = directoryList;
        this.noteBookList = noteBookList;
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

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public List<BriefDirectory> getDirectoryList() {
        return directoryList;
    }

    public void setDirectoryList(List<BriefDirectory> directoryList) {
        this.directoryList = directoryList;
    }

    public List<BriefDirectory> getNoteBookList() {
        return noteBookList;
    }

    public void setNoteBookList(List<BriefDirectory> noteBookList) {
        this.noteBookList = noteBookList;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", directoryList=" + directoryList +
                ", noteBookList=" + noteBookList +
                '}';
    }
}
