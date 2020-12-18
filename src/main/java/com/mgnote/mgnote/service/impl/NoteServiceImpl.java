package com.mgnote.mgnote.service.impl;

import com.google.common.base.Preconditions;
import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.exception.PermissionException;
import com.mgnote.mgnote.model.*;
import com.mgnote.mgnote.model.dto.GetNoteOutput;
import com.mgnote.mgnote.model.dto.ListPage;
import com.mgnote.mgnote.model.dto.ListParam;
import com.mgnote.mgnote.repository.NoteBookRepository;
import com.mgnote.mgnote.repository.NoteContentRepository;
import com.mgnote.mgnote.repository.NoteRepository;
import com.mgnote.mgnote.repository.UserRepository;
import com.mgnote.mgnote.service.NoteService;
import com.mgnote.mgnote.util.EntityUtil;
import com.mgnote.mgnote.util.ExampleUtil;
import com.mgnote.mgnote.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public String addNote(String userId, String noteBookId, Note note, List<String> noteContents) {
        Preconditions.checkNotNull(userId, "未输入用户id");
        Preconditions.checkNotNull(noteBookId, "未输入笔记本id");
        Preconditions.checkNotNull(note, "未输入笔记信息");
        Preconditions.checkNotNull(noteContents, "未输入笔记内容");
        Optional<NoteBook> opt = noteBookRepository.findById(noteBookId);
        Optional<User> opt2 = userRepository.findById(userId);
        if(!opt.isPresent() || !opt2.isPresent()){
            throw new EntityNotExistException("笔记本或用户不存在");
        }
        NoteBook noteBook = opt.get();
        if(!noteBook.getUserId().equals(userId)){
            throw new PermissionException("无权访问笔记本");
        }
        String id = UUID.randomUUID().toString();
        note.setId(id);
        note.setUserInfo(new BriefUser(opt2.get()));
        note.setContents(noteContents);
        Note after = EntityUtil.copyProperties(new Note(), note, true);
        noteBook.getNotes().add(new BriefNote(after));
        after.setPrevNoteBook(new BriefNoteBook(noteBook));
        after.setPrevNote(null);
        noteRepository.save(after);
        noteBookRepository.save(noteBook);
        return id;
    }

    @Override
    public String addSubNote(String noteId, Note note, List<String> noteContents) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Preconditions.checkNotNull(note, "未输入笔记信息");
        Preconditions.checkNotNull(noteContents, "未输入笔记内容");
        Optional<Note> opt = noteRepository.findById(noteId);
        if(!opt.isPresent()){
            throw new EntityNotExistException("父笔记不存在");
        }
        String id = UUID.randomUUID().toString();
        note.setId(id);
        note.setPrevNote(new BriefNote(opt.get()));
        note.setPrevNoteBook(null);
        note.setContents(noteContents);
        Note superNote = opt.get();
        superNote.getSubNotes().add(new BriefNote(note));
        note.setUserInfo(superNote.getUserInfo());
        Note after = EntityUtil.copyProperties(new Note(), note, true);
        noteRepository.save(after);
        noteRepository.save(superNote);
        return id;
    }

    @Override
    public Note getNoteInfoById(String noteId) throws EntityNotExistException {
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
        noteContentRepository.deleteAllByIdIn(note.getContents());
    }

    @Override
    public void updateNoteById(String id, Note note) {
        Preconditions.checkNotNull(id, "未输入笔记id");
        Preconditions.checkNotNull(note, "未输入笔记信息");
        updateNoteInfoById(id, note);
    }

    @Override
    public void setNotePublic(String noteId, Boolean isPublic) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Optional<Note> noteOpt = noteRepository.findById(noteId);
        if(!noteOpt.isPresent()){
            throw new EntityNotExistException("笔记不存在");
        }
        Note note = noteOpt.get();
        note.setPublic(isPublic);
        noteRepository.save(note);
    }

    @Override
    public GetNoteOutput getNoteById(String id) {
        Preconditions.checkNotNull(id, "未输入笔记id");
        Optional<Note> noteOpt = noteRepository.findById(id);
        Optional<NoteContent> noteContentOpt = noteContentRepository.findById(id);
        if(noteOpt.isPresent() && !noteOpt.get().getDeleted() && noteContentOpt.isPresent())
            return new GetNoteOutput(noteOpt.get(), noteContentOpt.get());
        throw new EntityNotExistException("笔记信息或笔记内容不存在");
    }

    @Override
    public List<Note> getNotesById(List<String> ids) {
        Preconditions.checkNotNull(ids, "未输入笔记信息id列表");
        return noteRepository.findAllByIdIn(ids);
    }

    @Override
    public void deleteNoteBatch(List<String> ids) {
        Preconditions.checkNotNull(ids, "未输入id列表");
        for(String id:ids) {
            deleteNote(id);
        }
    }

    @Override
    public ListPage<Note> searchNoteInfo(Note note, ListParam listParam, Boolean isPublic) {
        Preconditions.checkNotNull(note, "未输入笔记信息");
        Preconditions.checkNotNull(listParam, "未输入分页参数");
        Page<Note> page = noteRepository.findAll(ExampleUtil.getNoteExample(note, isPublic), ListUtil.getPageableByListParam(listParam));
        return new ListPage<Note>(page.toList(), ListUtil.getListParamByPage(page));
    }

    @Override
    public void addNoteContentsInNote(String noteId, List<String> noteContents) {
        Preconditions.checkNotNull(noteId, "笔记id");
        Preconditions.checkNotNull(noteContents, "笔记内容列表");
        Optional<Note> opt = noteRepository.findById(noteId);
        if(!opt.isPresent()){
            throw new EntityNotExistException("笔记不存在");
        }
        Note note = opt.get();
        List<String> ids = note.getContents();
        ids.addAll(noteContents);
        note.setContents(ids);
        noteRepository.save(note);
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
