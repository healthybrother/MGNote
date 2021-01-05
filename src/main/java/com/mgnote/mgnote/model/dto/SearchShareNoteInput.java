package com.mgnote.mgnote.model.dto;

import com.mgnote.mgnote.model.ShareNote;

public class SearchShareNoteInput {
    private ShareNote shareNote;
    private ListParam listParam;

    public SearchShareNoteInput(ShareNote shareNote, ListParam listParam) {
        this.shareNote = shareNote;
        this.listParam = listParam;
    }

    public SearchShareNoteInput() {
    }

    public ShareNote getShareNote() {
        return shareNote;
    }

    public void setShareNote(ShareNote shareNote) {
        this.shareNote = shareNote;
    }

    public ListParam getListParam() {
        return listParam;
    }

    public void setListParam(ListParam listParam) {
        this.listParam = listParam;
    }

    @Override
    public String toString() {
        return "SearchShareNoteInput{" +
                "shareNote=" + shareNote +
                ", listParam=" + listParam +
                '}';
    }
}
