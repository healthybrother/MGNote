package com.mgnote.mgnote.model.dto;

import com.mgnote.mgnote.model.Note;

public class SearchNoteInput {
    private Note note;
    private ListParam listParam;

    public SearchNoteInput(Note note, ListParam listParam) {
        this.note = note;
        this.listParam = listParam;
    }

    public SearchNoteInput() {
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public ListParam getListParam() {
        return listParam;
    }

    public void setListParam(ListParam listParam) {
        this.listParam = listParam;
    }

    @Override
    public String toString() {
        return "SearchNoteInput{" +
                "note=" + note +
                ", listParam=" + listParam +
                '}';
    }
}
