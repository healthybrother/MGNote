package com.mgnote.mgnote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "directory")
public class Directory {
    @Id
    private String id;
    private String name;
    private List<BriefDirectory> directories;
    private List<NoteBook> noteBooks;
    private BriefDirectory prevDirectory;
    private String userId;

    public Directory(String id, String name, List<BriefDirectory> directories, List<NoteBook> noteBooks, BriefDirectory prevDirectory, String userId) {
        this.id = id;
        this.name = name;
        this.directories = directories;
        this.noteBooks = noteBooks;
        this.prevDirectory = prevDirectory;
        this.userId = userId;
    }

    public Directory() {
    }

    public Directory(BriefDirectory briefDirectory){
        this.id = briefDirectory.getId();
        this.name = briefDirectory.getName();
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

    public List<BriefDirectory> getDirectories() {
        return directories;
    }

    public void setDirectories(List<BriefDirectory> directories) {
        this.directories = directories;
    }

    public List<NoteBook> getNoteBooks() {
        return noteBooks;
    }

    public void setNoteBooks(List<NoteBook> noteBooks) {
        this.noteBooks = noteBooks;
    }

    public BriefDirectory getPrevDirectory() {
        return prevDirectory;
    }

    public void setPrevDirectory(BriefDirectory prevDirectory) {
        this.prevDirectory = prevDirectory;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", directories=" + directories +
                ", noteBooks=" + noteBooks +
                ", prevDirectory=" + prevDirectory +
                ", userId='" + userId + '\'' +
                '}';
    }
}
