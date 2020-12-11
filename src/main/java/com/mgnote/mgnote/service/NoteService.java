package com.mgnote.mgnote.service;

import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.Note;

public interface NoteService {

    String addNote(Note note);

    Note getNoteById(String noteId) throws EntityNotExistException;
}
