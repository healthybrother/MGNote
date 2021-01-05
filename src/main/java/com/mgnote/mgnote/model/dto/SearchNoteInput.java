package com.mgnote.mgnote.model.dto;

import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.SubNote;

public class SearchNoteInput {
    private Note note;
    private SubNote subNote;
    private ListParam listParam;

    public SearchNoteInput(Note note, SubNote subNote, ListParam listParam) {
        this.note = note;
        this.subNote = subNote;
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

    public SubNote getSubNote() {
        return subNote;
    }

    public void setSubNote(SubNote subNote) {
        this.subNote = subNote;
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
                ", subNote=" + subNote +
                ", listParam=" + listParam +
                '}';
    }
}
