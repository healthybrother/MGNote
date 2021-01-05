package com.mgnote.mgnote.model.dto;

import com.mgnote.mgnote.model.SubNote;

public class BriefNote {
    private String id;
    private String name;

    public BriefNote() {
    }

    public BriefNote(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public BriefNote(SubNote subNote){
        this.id = subNote.getId();
        this.name = subNote.getName();
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
        return "BriefNote{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
