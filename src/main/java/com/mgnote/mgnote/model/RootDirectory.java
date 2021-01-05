package com.mgnote.mgnote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "root_directory")
public class RootDirectory{
    @Id
    private String id;
    private String name;
    private boolean del;
    private String userId;

    public RootDirectory() {
    }

    public RootDirectory(String id, String name, boolean del, String userId) {
        this.id = id;
        this.name = name;
        this.del = del;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RootDirectory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", del=" + del +
                ", userId='" + userId + '\'' +
                '}';
    }
}
