package com.mgnote.mgnote.service.impl;

import com.google.common.base.Preconditions;
import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.*;
import com.mgnote.mgnote.repository.NoteBookRepository;
import com.mgnote.mgnote.repository.NoteContentRepository;
import com.mgnote.mgnote.repository.NoteRepository;
import com.mgnote.mgnote.repository.UserRepository;
import com.mgnote.mgnote.service.NoteService;
import com.mgnote.mgnote.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
    private NoteRepository noteRepository;
    private UserRepository userRepository;
    private NoteBookRepository noteBookRepository;
    private NoteContentRepository noteContentRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository, NoteBookRepository noteBookRepository,
                           NoteContentRepository noteContentRepository){
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.noteBookRepository = noteBookRepository;
        this.noteContentRepository = noteContentRepository;
    }

    @Override
    public String addNote(String userId, String noteBookId, Note note, NoteContent noteContent) {
        Preconditions.checkNotNull(userId, "未输入用户id");
        Preconditions.checkNotNull(noteBookId, "未输入笔记本id");
        Preconditions.checkNotNull(note, "未输入笔记信息");
        Preconditions.checkNotNull(noteContent, "未输入笔记内容");
        Optional<NoteBook> opt = noteBookRepository.findById(noteBookId);
        Optional<User> opt2 = userRepository.findById(userId);
        if(!opt.isPresent() || !opt2.isPresent()){
            throw new EntityNotExistException("笔记本或用户不存在");
        }
        String id = UUID.randomUUID().toString();
        note.setId(id);
        noteContent.setId(id);
        note.setUserInfo(new BriefUser(opt2.get()));
        Note after = EntityUtil.copyProperties(new Note(), note, true);
        NoteBook noteBook = opt.get();
        noteBook.getNotes().add(new BriefNote(after));
        after.setPrevNoteBook(new BriefNoteBook(noteBook));
        after.setPrevNote(null);
        noteRepository.save(after);
        noteBookRepository.save(noteBook);
        noteContentRepository.save(noteContent);
        return id;
    }

    @Override
    public String addSubNote(String noteId, Note note, NoteContent noteContent) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Preconditions.checkNotNull(note, "未输入笔记信息");
        Preconditions.checkNotNull(noteContent, "未输入笔记内容");
        Optional<Note> opt = noteRepository.findById(noteId);
        if(!opt.isPresent()){
            throw new EntityNotExistException("父笔记不存在");
        }
        String id = UUID.randomUUID().toString();
        note.setId(id);
        noteContent.setId(id);
        note.setPrevNote(new BriefNote(opt.get()));
        note.setPrevNoteBook(null);
        Note superNote = opt.get();
        superNote.getSubNotes().add(new BriefNote(note));
        Note after = EntityUtil.copyProperties(new Note(), note, true);
        noteRepository.save(after);
        noteRepository.save(superNote);
        noteContentRepository.save(noteContent);
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
            after.setDeleted(false);
            after.setUpdatedAt(new Date());
            noteRepository.save(after);
        }
        throw new EntityNotExistException("符合id的笔记不存在");
    }

    //TODO:未删除笔记本或父笔记中的BriefNote，这里应该有更好的设计
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
        Optional<Note> opt = noteRepository.findById(noteId);
        if(!opt.isPresent()) return;
        Note note = opt.get();
        if(note.getPrevNote() != null){
            Note fatherNote = noteRepository.findById(note.getPrevNote().getId()).get();
            deleteFromNoteList(fatherNote.getSubNotes(), noteId);
            noteRepository.save(fatherNote);
        }
        else if(note.getPrevNoteBook() != null){
            NoteBook noteBook = noteBookRepository.findById(note.getPrevNote().getId()).get();
            deleteFromNoteList(noteBook.getNotes(), noteId);
            noteBookRepository.save(noteBook);
        }
        noteRepository.deleteById(noteId);
    }

    private void deleteFromNoteList(List<BriefNote> list, String noteId){
        for (BriefNote briefNote: list) {
            if(briefNote.getId().equals(noteId)){
                list.remove(briefNote);
                return;
            }
        }
    }
}
