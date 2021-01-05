package com.mgnote.mgnote.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "directory")
public class RootDirectory extends Directory{
    private String userId;

    public RootDirectory() {
    }

    public RootDirectory(String userId) {
        this.userId = userId;
    }

    public RootDirectory(String id, String name, boolean del, List<BriefDirectory> directoryList, List<BriefDirectory> noteBookList, String userId) {
        super(id, name, del, directoryList, noteBookList);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RootDirectory{" +
                "id='" + getId() + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + getName() + '\'' +
                ", directoryList=" + getDirectoryList() +
                ", noteBookList=" + getNoteBookList() +
                '}';
    }
}
