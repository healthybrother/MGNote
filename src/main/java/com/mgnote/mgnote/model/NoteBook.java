package com.mgnote.mgnote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "note_book")
public class NoteBook{
    @Id
    private String id;
    private String name;
    private boolean del;
    private List<String> noteIdList;

    public NoteBook() {
    }

    public NoteBook(String id, String name, boolean del, List<String> noteIdList) {
        this.id = id;
        this.name = name;
        this.del = del;
        this.noteIdList = noteIdList;
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
