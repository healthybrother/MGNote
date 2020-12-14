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
    public void updateNoteInfoById(String noteId, Note note) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Preconditions.checkNotNull(note, "未输入笔记信息");
        Optional<Note> opt = noteRepository.findById(noteId);
        if(opt.isPresent()){
            note.setId(noteId);
            Note after = EntityUtil.copyProperties(note, opt.get(), true);
            after.setDeleted(false);
            after.setUpdatedAt(new Date());
            noteRepository.save(after);
            if(!after.getTopic().equals(note.getTopic())){
                updateInPrev(after);
            }
        }
        throw new EntityNotExistException("符合id的笔记不存在");
    }

    //TODO:Note相关的应该有更好的设计
    @Override
    public void deleteNoteSoft(String noteId) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Optional<Note> opt = noteRepository.findById(noteId);
        if(opt.isPresent()){
            Note note = opt.get();
            note.setDeleted(true);
            noteRepository.save(note);
            deleteFromPrev(noteId, note);
        }
        throw new EntityNotExistException("符合id的笔记不存在");
    }

    @Override
    public void deleteNote(String noteId) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Optional<Note> opt = noteRepository.findById(noteId);
        if(!opt.isPresent()) return;
        Note note = opt.get();
        deleteFromPrev(noteId, note);
        noteRepository.deleteById(noteId);
    }

    @Override
    public void updateNoteContentById(String noteContentId, NoteContent noteContent) {
        Preconditions.checkNotNull(noteContentId, "未输入笔记内容id");
        Preconditions.checkNotNull(noteContent, "未输入笔记内容");
        Optional<NoteContent> opt = noteContentRepository.findById(noteContentId);
        if(opt.isPresent()){
            NoteContent content = opt.get();
            noteContent.setId(noteContentId);
            NoteContent after = EntityUtil.copyProperties(noteContent, content, true);
            noteContentRepository.save(after);
        }
        throw new EntityNotExistException("目标笔记内容不存在");
    }

    @Override
    public void updateNoteById(String id, Note noteInfo, NoteContent noteContent) {
        Preconditions.checkNotNull(id, "未输入笔记id");
        Preconditions.checkNotNull(noteInfo, "未输入笔记信息");
        Preconditions.checkNotNull(noteContent, "未输入笔记内容");
        updateNoteInfoById(id, noteInfo);
        updateNoteContentById(id, noteContent);
    }

    private void deleteFromPrev(String noteId, Note note) {
        if(note.getPrevNote() != null){
            Optional<Note> opt = noteRepository.findById(note.getPrevNote().getId());
            if(opt.isPresent()){
                Note fatherNote = opt.get();
                deleteFromPrevList(fatherNote.getSubNotes(), noteId);
                noteRepository.save(fatherNote);
            }
            else {
                throw new EntityNotExistException("父笔记本不存在！");
            }
        }
        else if(note.getPrevNoteBook() != null){
            Optional<NoteBook> opt = noteBookRepository.findById(note.getPrevNoteBook().getId());
            if(opt.isPresent()){
                NoteBook noteBook = opt.get();
                deleteFromPrevList(noteBook.getNotes(), noteId);
                noteBookRepository.save(noteBook);
            }
            else{
                throw new EntityNotExistException("父笔记不存在！");
            }
        }
    }

    private void deleteFromPrevList(List<BriefNote> list, String noteId){
        for (BriefNote briefNote: list) {
            if(briefNote.getId().equals(noteId)){
                list.remove(briefNote);
                return;
            }
        }
    }

    private void updateInPrev(Note note){
        List<BriefNote> prevList;
        if(note.getPrevNoteBook()!=null){
            Optional<NoteBook> opt = noteBookRepository.findById(note.getPrevNoteBook().getId());
            if(opt.isPresent()){
                NoteBook noteBook = opt.get();
                prevList = noteBook.getNotes();
            }
            else throw new EntityNotExistException("父笔记本不存在!");
        }
        else if(note.getPrevNote()!=null){
            Optional<Note> opt = noteRepository.findById(note.getPrevNote().getId());
            if(opt.isPresent()){
                Note fatherNote = opt.get();
                prevList = fatherNote.getSubNotes();
            }
            else throw new EntityNotExistException("父笔记不存在！");
        }
        else return;
        for (BriefNote briefNote:prevList) {
            if(briefNote.getId().equals(note.getId())){
                briefNote.setTopic(note.getTopic());
                return;
            }
        }
    }
}
