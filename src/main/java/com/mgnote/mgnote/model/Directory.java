package com.mgnote.mgnote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

public class Directory{
    private String id;
    private String name;
    private List<Directory> directoryList;
    private List<NoteBook> noteBookList;

    public Directory() {
    }

    public Directory(String id, String name, List<Directory> directoryList, List<NoteBook> noteBookList) {
        this.id = id;
        this.name = name;
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

    public List<Directory> getDirectoryList() {
        return directoryList;
    }

    public void setDirectoryList(List<Directory> directoryList) {
        this.directoryList = directoryList;
    }

    public List<NoteBook> getNoteBookList() {
        return noteBookList;
    }

    public void setNoteBookList(List<NoteBook> noteBookList) {
        this.noteBookList = noteBookList;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", directoryList=" + directoryList +
                ", noteBookList=" + noteBookList +
                '}';
    }
}
