package com.mgnote.mgnote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "root_directory")
public class RootDirectory{
    @Id
    private String id;
    private String userId;
    private List<Directory> directoryList;
    private List<NoteBook> noteBookList;

    public RootDirectory() {
    }

    public RootDirectory(String id, String userId, List<Directory> directoryList, List<NoteBook> noteBookList) {
        this.id = id;
        this.userId = userId;
        this.directoryList = directoryList;
        this.noteBookList = noteBookList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        return "RootDirectory{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", directoryList=" + directoryList +
                ", noteBookList=" + noteBookList +
                '}';
    }
}
