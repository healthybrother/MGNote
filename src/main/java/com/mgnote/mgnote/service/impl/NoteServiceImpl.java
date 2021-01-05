package com.mgnote.mgnote.service.impl;

import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.SubNote;
import com.mgnote.mgnote.repository.NoteRepository;
import com.mgnote.mgnote.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);
    private NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    @Override
    public String addNote(Note note) {
        return null;
    }

    @Override
    public String addSubNote(String noteId, SubNote note) {
        return null;
    }

    @Override
    public Note getNoteById(String noteId) {
        return null;
    }

    @Override
    public SubNote getSubNoteById(String noteId, String subNoteId) {
        return null;
    }

    @Override
    public List<SubNote> getSubNoteListByNoteId(String noteId) {
        return null;
    }

    @Override
    public void updateNoteById(String noteId, Note note) {

    }

    @Override
    public void updateSubNoteById(String noteId, String subNoteId, SubNote subNote) {

    }

    @Override
    public void deleteNote(String noteId) {

    }

    @Override
    public void deleteNoteBatch(List<String> noteIdList) {

    }

    @Override
    public void deleteSubNote(String noteId, String subNoteId) {

    }

    @Override
    public void deleteSubNoteBatch(String noteId, List<String> subNoteIdList) {

    }

    @Override
    public void searchSubNote(String noteId, String subNoteId, String pattern) {

    }
}
