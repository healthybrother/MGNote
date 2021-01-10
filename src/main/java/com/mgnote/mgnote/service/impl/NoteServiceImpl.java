package com.mgnote.mgnote.service.impl;

import com.google.common.base.Preconditions;
import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.*;
import com.mgnote.mgnote.model.dto.BriefNote;
import com.mgnote.mgnote.model.dto.ListParam;
import com.mgnote.mgnote.repository.*;
import com.mgnote.mgnote.service.DirectoryService;
import com.mgnote.mgnote.service.NoteService;
import com.mgnote.mgnote.util.EntityUtil;
import com.mgnote.mgnote.util.ExampleUtil;
import com.mgnote.mgnote.util.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
    private final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);
    private NoteRepository noteRepository;
    private SubNoteRepository subNoteRepository;
    private DirectoryService directoryService;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, SubNoteRepository subNoteRepository, DirectoryService directoryService){
        this.noteRepository = noteRepository;
        this.subNoteRepository = subNoteRepository;
        this.directoryService = directoryService;
    }

    @Override
    public String addSubNote(String path, SubNote note) {
        Preconditions.checkNotNull(path, "未输入路径信息");
        Preconditions.checkNotNull(note, "未输入笔记信息");
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        note.setId(id);
        note.setPath(path);
        SubNote after = EntityUtil.copyProperties(SubNote.getNewSubNote(), note, true);
        subNoteRepository.save(after);
        return id;
    }

    @Override
    public Note getNoteById(String noteId) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Optional<Note> opt = noteRepository.findById(noteId);
        if(opt.isPresent()) return opt.get();
        throw new EntityNotExistException("Note不存在");
    }

    @Override
    public List<SubNote> getSubNoteListByNoteId(String noteId) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        String regex = "/"+noteId+"/";
        return subNoteRepository.findAllByPathRegex(regex);
    }

    @Override
    public SubNote getSubNoteById(String subNoteId) {
        Preconditions.checkNotNull(subNoteId, "未输入笔记id");
        Optional<SubNote> opt = subNoteRepository.findById(subNoteId);
        if(opt.isPresent()) return opt.get();
        throw new EntityNotExistException("笔记不存在");
    }

    @Override
    public void updateNoteById(String path, String noteId, Note note) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Preconditions.checkNotNull(note, "未输入笔记信息");
        Optional<Note> opt = noteRepository.findById(noteId);
        if(opt.isPresent() && !opt.get().getDel()){
            Note note1 = opt.get();
            if(note.getName()!=null && note1.getName().equals(note.getName())) {
                directoryService.updateNoteInfo(path, new BriefNote(noteId, note.getName()));
            }
            EntityUtil.copyProperties(Note.getUpdateNote(), note, false);
            note.setDel(false);
            note.setId(noteId);
            note.setUpdateTime(new Date(System.currentTimeMillis()));
            Note after = EntityUtil.copyProperties(note, note1, true);
            noteRepository.save(after);
        }
        throw new EntityNotExistException("笔记不存在");
    }

    @Override
    public void updateSubNoteById(String subNoteId, SubNote subNote) {
        Preconditions.checkNotNull(subNoteId, "未输入笔记id");
        Preconditions.checkNotNull(subNote, "未输入笔记信息");
        Optional<SubNote> opt = subNoteRepository.findById(subNoteId);
        if(opt.isPresent()){
            SubNote old = opt.get();
            subNote.setId(subNoteId);
            subNote.setDel(false);
            subNote.setPath(old.getPath());
            subNote = EntityUtil.copyProperties(subNote, old, true);
            subNoteRepository.save(subNote);
            updateNoteUpdateTime(subNote.getPath());
        }
        else throw new EntityNotExistException("笔记不存在");
    }

    @Override
    public void deleteNote(String noteId) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Note delete = getNoteById(noteId);
        noteRepository.deleteById(noteId);
    }

    @Override
    public void deleteNoteBatch(List<String> noteIdList) {
        Preconditions.checkNotNull(noteIdList, "未输入笔记id列表");
        noteRepository.deleteAllByIdIn(noteIdList);
    }

    @Override
    public void deleteSubNote(String subNoteId) {
        Preconditions.checkNotNull(subNoteId, "未输入笔记id");

        subNoteRepository.deleteById(subNoteId);
    }

    @Override
    public void deleteSubNoteBatch(List<String> subNoteIdList) {
        Preconditions.checkNotNull(subNoteIdList, "未输入笔记id列表");
        subNoteRepository.deleteAllByIdIn(subNoteIdList);
    }

    @Override
    public Page<SubNote> searchSubNote(SubNote pattern, ListParam listParam) {
        Preconditions.checkNotNull(pattern, "未输入匹配笔记");
        Preconditions.checkNotNull(listParam, "未输入分页信息");
        Example<SubNote> example = ExampleUtil.getSubNoteExample(pattern);
        return subNoteRepository.findAll(example, ListUtil.getPageableByListParam(listParam));
    }

    @Override
    public List<SubNote> matchPathSubNotes(String path, boolean all) {
        Preconditions.checkNotNull(path, "未输入匹配路径");
        List<SubNote> list;
        if(!all) list = subNoteRepository.findAllByPathRegex(path);
        else{
            String regex = "^"+path;
            list = subNoteRepository.findAllByPathRegex(regex);
        }
        return list;
    }

    @Override
    public Page<Note> searchNotes(Note note, ListParam listParam) {
        Preconditions.checkNotNull(note, "未输入筛选信息");
        Preconditions.checkNotNull(listParam, "未输入分页信息");
        Example<Note> example = ExampleUtil.getNoteExample(note);
        return noteRepository.findAll(example, ListUtil.getPageableByListParam(listParam));
    }

    @Override
    public Page<Note> search(String keyword, ListParam listParam) {
        Preconditions.checkNotNull(keyword, "未输入搜索关键词");
        Preconditions.checkNotNull(listParam, "未输入分页信息");
        return null;
    }

    @Override
    public void updateNoteUpdateTime(String path) {
        String[] idList = path.split("/");
        Optional<Note> opt = noteRepository.findById(idList[1]);
        if(opt.isPresent() && !opt.get().getDel()){
            Note note = opt.get();
            note.setUpdateTime(new Date(System.currentTimeMillis()));
            noteRepository.save(note);
        }
        throw new EntityNotExistException("笔记不存在(更新笔记时间时出错)");
    }
}
