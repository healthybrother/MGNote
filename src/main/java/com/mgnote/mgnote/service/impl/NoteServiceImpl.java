package com.mgnote.mgnote.service.impl;

import com.google.common.base.Preconditions;
import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.repository.NoteRepository;
import com.mgnote.mgnote.service.NoteService;
import com.mgnote.mgnote.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
    private NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    @Override
    public String addNote(Note note) {
        Preconditions.checkNotNull(note, "未输入笔记信息");
        String id = UUID.randomUUID().toString();
        note.setId(id);
        EntityUtil.copyProperties(new Note(), note, true);
        noteRepository.save(note);
        return id;
    }

    @Override
    public Note getNoteById(String noteId) throws EntityNotExistException {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Optional<Note> opt = noteRepository.findById(noteId);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new EntityNotExistException("符合id的笔记不存在");
    }

    @Override
    public void updateNoteBy(String noteId, Note note) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Preconditions.checkNotNull(note, "未输入笔记信息");
        Optional<Note> opt = noteRepository.findById(noteId);
        if(opt.isPresent()){
            note.setId(noteId);
            Note after = EntityUtil.copyProperties(note, opt.get(), true);
            noteRepository.save(after);
        }
        throw new EntityNotExistException("符合id的笔记不存在");
    }

    @Override
    public void deleteNoteSoft(String noteId) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Optional<Note> opt = noteRepository.findById(noteId);
        if(opt.isPresent()){
            Note note = opt.get();
            note.setDeleted(true);
            noteRepository.save(note);
        }
        throw new EntityNotExistException("符合id的笔记不存在");
    }

    @Override
    public void deleteNote(String noteId) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        noteRepository.deleteById(noteId);
    }
}
