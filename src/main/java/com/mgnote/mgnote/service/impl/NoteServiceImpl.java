package com.mgnote.mgnote.service.impl;

import com.google.common.base.Preconditions;
import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.NoteBook;
import com.mgnote.mgnote.model.SubNote;
import com.mgnote.mgnote.model.dto.BriefNote;
import com.mgnote.mgnote.model.dto.ListParam;
import com.mgnote.mgnote.repository.NoteBookRepository;
import com.mgnote.mgnote.repository.NoteRepository;
import com.mgnote.mgnote.repository.SubNoteRepository;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
    private final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);
    private NoteRepository noteRepository;
    private SubNoteRepository subNoteRepository;
    private NoteBookRepository noteBookRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, SubNoteRepository subNoteRepository, NoteBookRepository noteBookRepository){
        this.noteRepository = noteRepository;
        this.subNoteRepository = subNoteRepository;
        this.noteBookRepository = noteBookRepository;
    }

    @Override
    public String addSubNote(String path, SubNote note) {
        Preconditions.checkNotNull(path, "未输入路径信息");
        Preconditions.checkNotNull(note, "未输入笔记信息");
        String id = Arrays.toString(UUID.randomUUID().toString().split("-"));
        note.setId(id);
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
        List<SubNote> list = subNoteRepository.findAllByPathRegex(regex);
        return list;
    }

    @Override
    public SubNote getSubNoteById(String subNoteId) {
        Preconditions.checkNotNull(subNoteId, "未输入笔记id");
        Optional<SubNote> opt = subNoteRepository.findById(subNoteId);
        if(opt.isPresent()) return opt.get();
        throw new EntityNotExistException("笔记不存在");
    }

    @Override
    public void updateNoteById(String noteBook, String noteId, Note note) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Preconditions.checkNotNull(note, "未输入笔记信息");
        Optional<Note> opt = noteRepository.findById(noteId);
        if(opt.isPresent() && !opt.get().isDel()){
            Note note1 = opt.get();
            if(note.getName()!=null && note1.getName().equals(note.getName())) updateNameInNoteBook(noteBook, noteId, note.getName());
            note = EntityUtil.copyProperties(Note.getUpdateNote(), note, false);
            note.setDel(false);
            note.setId(noteId);
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
        }
        else throw new EntityNotExistException("笔记不存在");
    }

    @Override
    public void deleteNote(String noteId) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
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
        Page<SubNote> page = subNoteRepository.findAll(example, ListUtil.getPageableByListParam(listParam));
        return page;
    }

    private void updateNameInNoteBook(String notebookId, String noteId, String name){
        Preconditions.checkNotNull(notebookId, "未输入笔记本id");
        Optional<NoteBook> opt = noteBookRepository.findById(notebookId);
        if(opt.isPresent()){
            List<BriefNote> briefNotes = opt.get().getNoteIdList();
            for(BriefNote briefNote:briefNotes){
                if(briefNote.getId().equals(noteId)){
                    briefNote.setName(name);
                    break;
                }
            }
            noteBookRepository.save(opt.get());
        }
        else throw new EntityNotExistException("笔记本不存在");
    }
}
