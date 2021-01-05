package com.mgnote.mgnote.service;

import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.ShareNote;
import com.mgnote.mgnote.model.SubNote;
import com.mgnote.mgnote.model.dto.ListParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoteService {

    /**
     * 在subNote中添加一个subNote， 即添加一个二级及以上的subNote
     * @param path 所要添加的subNote所在的路径
     * @param subNote 所要添加的subNote
     * @return 新添加subNote的id
     */
    String addSubNote(String path, SubNote subNote);

    /**
     * 通过noteId获取note
     * @param noteId noteId
     * @return 获取的note
     */
    Note getNoteById(String noteId);

    /**
     * 通过subNoteId和noteId获取subNote
     * @param subNoteId subNoteId
     * @return 获取的subNote
     */
    SubNote getSubNoteById(String subNoteId);

    /**
     * 通过noteId获取subNote列表
     * @param noteId noteId
     * @return subNote列表
     */
    List<SubNote> getSubNoteListByNoteId(String noteId);

    /**
     * 更新note，不允许通过此方法添加subNote
     * @param noteBook 笔记本id
     * @param noteId 将要更新的noteId
     * @param note 更新的note
     */
    void updateNoteById(String noteBook, String noteId, Note note);

    /**
     * 根据noteId和subNoteId更新subNote,不允许通过此方法添加subNote
     * @param subNoteId 将要更新的subNoteId
     * @param subNote 更新的subNote
     */
    void updateSubNoteById(String subNoteId, SubNote subNote);

    /**
     * 删除note(软删除?)
     * @param noteId noteId
     */
    void deleteNote(String noteId);

    /**
     * 批量删除note
     * @param noteIdList noteId列表
     */
    void deleteNoteBatch(List<String> noteIdList);

    /**
     * 删除subNote(软删除?)
     * @param subNoteId subNoteId
     */
    void deleteSubNote(String subNoteId);

    /**
     * 批量删除subNote
     * @param subNoteIdList subNoteId列表
     */
    void deleteSubNoteBatch(List<String> subNoteIdList);

    /**
     * 搜索subNote
     * @param pattern 匹配模式
     */
    Page<SubNote> searchSubNote(SubNote pattern, ListParam listParam);

    List<SubNote> matchPathSubNotes(String path, boolean all);

    Page<ShareNote> searchShareNotes(ShareNote shareNote, ListParam listParam);

    Page<Note> searchNotes(Note note, ListParam listParam);

    Page<Note> search(String keyword, ListParam listParam);
}
