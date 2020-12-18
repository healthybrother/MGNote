package com.mgnote.mgnote.service.impl;

import com.google.common.base.Preconditions;
import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.NoteContent;
import com.mgnote.mgnote.repository.NoteContentRepository;
import com.mgnote.mgnote.repository.NoteRepository;
import com.mgnote.mgnote.service.NoteContentService;
import com.mgnote.mgnote.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteContentServiceImpl implements NoteContentService {
    private NoteContentRepository noteContentRepository;
    private NoteRepository noteRepository;

    @Autowired
    public NoteContentServiceImpl(NoteContentRepository noteContentRepository, NoteRepository noteRepository){
        this.noteContentRepository = noteContentRepository;
        this.noteRepository = noteRepository;
    }

    @Override
    public NoteContent getNoteContentById(String noteContentId) {
        Preconditions.checkNotNull(noteContentId, "未输入笔记内容id");
        Optional<NoteContent> opt = noteContentRepository.findById(noteContentId);
        if(opt.isPresent()) return opt.get();
        throw new EntityNotExistException("笔记内容不存在");
    }

    @Override
    public List<NoteContent> getNoteContentsById(List<String> ids) {
        Preconditions.checkNotNull(ids, "未输入笔记内容id列表");
        return noteContentRepository.findAllByIdIn(ids);
    }

    @Override
    public List<String> addNoteContents(List<NoteContent> noteContents) {
        Preconditions.checkNotNull(noteContents, "未输入笔记内容");
        List<String> ids = new ArrayList<>();
        for(NoteContent noteContent:noteContents){
            String id = UUID.randomUUID().toString();
            noteContent.setId(id);
            ids.add(id);
        }
        noteContentRepository.saveAll(noteContents);
        return ids;
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
    public void updateNoteContentsById(List<NoteContent> noteContents) {
        Preconditions.checkNotNull(noteContents, "未输入笔记内容列表");
        for(NoteContent noteContent:noteContents){
            updateNoteContentById(noteContent.getId(), noteContent);
        }
    }

    @Override
    public void deleteContentById(String contentId) {
        Preconditions.checkNotNull(contentId, "未输入内容id");
        noteContentRepository.deleteById(contentId);
    }
}
