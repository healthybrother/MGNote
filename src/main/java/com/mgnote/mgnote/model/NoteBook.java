package com.mgnote.mgnote.model;

import com.mgnote.mgnote.model.dto.BriefNote;

import java.util.List;

public class NoteBook{
    private String id;
    private String name;
    private List<BriefNote> noteList;

    public NoteBook() {
    }

    public NoteBook(String id, String name, List<BriefNote> noteList) {
        this.id = id;
        this.name = name;
        this.noteList = noteList;
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

    public List<BriefNote> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<BriefNote> noteList) {
        this.noteList = noteList;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", noteIdList=" + noteList +
                '}';
    }
}
