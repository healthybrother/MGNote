package com.mgnote.mgnote.service.impl;

import com.google.common.base.Preconditions;
import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.AbstractNote;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.SubNote;
import com.mgnote.mgnote.repository.AbstractNoteRepository;
import com.mgnote.mgnote.repository.NoteRepository;
import com.mgnote.mgnote.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
    private final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);
    private NoteRepository noteRepository;
    private AbstractNoteRepository abstractNoteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, AbstractNoteRepository abstractNoteRepository){
        this.noteRepository = noteRepository;
        this.abstractNoteRepository = abstractNoteRepository;
    }

    @Override
    public String addNote(Note note) {
        Preconditions.checkNotNull(note, "未输入笔记信息");
        log.info("note: {}", note);
        String id = UUID.randomUUID().toString();
        note.setId(id);
        note.init();
        abstractNoteRepository.save(note);
        return id;
    }

    @Override
    public String addSubNote(String noteId, SubNote note) {
        return null;
    }

    @Override
    public Note getNoteById(String noteId) {
        Preconditions.checkNotNull(noteId, "未输入id信息");
        Optional<AbstractNote> opt = abstractNoteRepository.findById(noteId);
        if(opt.isPresent()){
            AbstractNote abstractNote = opt.get();
            if(abstractNote instanceof Note) {
                return (Note) abstractNote;
            }
            else throw new EntityNotExistException("笔记不存在");
        }
        else throw new EntityNotExistException("笔记不存在");
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
