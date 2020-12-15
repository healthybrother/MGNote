package com.mgnote.mgnote.model.dto;

import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.NoteContent;

public class GetNoteOutput {
    private Note note;
    private NoteContent noteContent;

    public GetNoteOutput(Note note, NoteContent noteContent) {
        this.note = note;
        this.noteContent = noteContent;
    }

    public GetNoteOutput() {
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public NoteContent getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(NoteContent noteContent) {
        this.noteContent = noteContent;
    }

    @Override
    public String toString() {
        return "GetNoteOutput{" +
                "note=" + note +
                ", noteContent=" + noteContent +
                '}';
    }
}
