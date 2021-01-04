package com.mgnote.mgnote.model;

import java.util.List;

public class Directory extends AbstractDirectory{
    private List<Directory> directoryList;
    private List<NoteBook> noteBookList;

    public Directory(){}

    public Directory(String id, String name, boolean del, List<Directory> directoryList, List<NoteBook> noteBookList) {
        super(id, name, del);
        this.directoryList = directoryList;
        this.noteBookList = noteBookList;
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
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", directoryList=" + directoryList +
                ", noteBookList=" + noteBookList +
                '}';
    }
}
