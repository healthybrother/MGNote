package com.mgnote.mgnote.model;

import java.util.List;

public class NoteBook extends AbstractDirectory{
    private List<String> noteIdList;

    public NoteBook() {
    }

    public NoteBook(String id, String name, boolean del, List<String> noteIdList) {
        super(id, name, del);
        this.noteIdList = noteIdList;
    }

    public List<String> getNoteIdList() {
        return noteIdList;
    }

    public void setNoteIdList(List<String> noteIdList) {
        this.noteIdList = noteIdList;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", noteIdList=" + noteIdList +
                '}';
    }
}
