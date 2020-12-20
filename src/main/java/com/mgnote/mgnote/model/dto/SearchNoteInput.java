package com.mgnote.mgnote.model.dto;

import com.mgnote.mgnote.model.BriefNote;
import com.mgnote.mgnote.model.Note;

public class SearchNoteInput {
    private BriefNote briefNote;
    private ListParam listParam;

    public SearchNoteInput(BriefNote briefNote, ListParam listParam) {
        this.briefNote = briefNote;
        this.listParam = listParam;
    }

    public SearchNoteInput() {
    }

    public BriefNote getBriefNote() {
        return briefNote;
    }

    public void setBriefNote(BriefNote briefNote) {
        this.briefNote = briefNote;
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
                "briefNote=" + briefNote +
                ", listParam=" + listParam +
                '}';
    }
}
