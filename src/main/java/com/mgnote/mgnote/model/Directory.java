package com.mgnote.mgnote.model;

import java.util.List;

public class Directory {
    private String id;
    private String name;
    private List<BriefDirectory> directories;
    private List<NoteBook> noteBooks;
    private BriefDirectory prevDirectory;

    public Directory(String id, String name, List<BriefDirectory> directories, List<NoteBook> noteBooks, BriefDirectory prevDirectory) {
        this.id = id;
        this.name = name;
        this.directories = directories;
        this.noteBooks = noteBooks;
        this.prevDirectory = prevDirectory;
    }

    public Directory() {
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

    @Override
    public String toString() {
        return "Directory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", directories=" + directories +
                ", noteBooks=" + noteBooks +
                ", prevDirectory=" + prevDirectory +
                '}';
    }
}
