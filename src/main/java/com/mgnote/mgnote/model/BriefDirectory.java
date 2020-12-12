package com.mgnote.mgnote.model;

public class BriefDirectory {
    private String id;
    private String name;

    public BriefDirectory(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public BriefDirectory() {
    }

    public BriefDirectory(Directory directory){
        this.id = directory.getId();
        this.name = directory.getName();
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

    @Override
    public String toString() {
        return "BriefDirectory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
