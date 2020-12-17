package com.mgnote.mgnote.model.dto;

import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.NoteContent;

import java.util.List;

public class AddNoteInput {
    private String userId;
    private String id;
    private Note note;
    private List<NoteContent> noteContents;

    public AddNoteInput(String userId, String id, Note note, List<NoteContent> noteContents) {
        this.userId = userId;
        this.id = id;
        this.note = note;
        this.noteContents = noteContents;
    }

    public AddNoteInput() {
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public List<NoteContent> getNoteContents() {
        return noteContents;
    }

    public void setNoteContents(List<NoteContent> noteContents) {
        this.noteContents = noteContents;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AddNoteInput{" +
                "userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", note=" + note +
                ", noteContents=" + noteContents +
                '}';
    }
}
