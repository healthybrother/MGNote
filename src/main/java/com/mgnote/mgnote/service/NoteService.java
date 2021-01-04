package com.mgnote.mgnote.service;

import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.SubNote;

import java.util.List;

public interface NoteService {

    /**
     * 在subNote中添加一个subNote， 即添加一个二级及以上的subNote
     * @param noteId 所要添加的subNote所在的subNote所在的Note的id
     * @param subNoteId 所要添加的subNote所在的subNote的id
     * @param subNote 所要添加的subNote
     * @return 新添加subNote的id
     */
    String addSubNote(String noteId, String subNoteId, SubNote subNote);

    /**
     * 在note中添加一个subNote，即添加一个一级的subNote
     * @param noteId 所要添加的subNote所在的subNote的id
     * @param subNote 所要添加的subNote
     * @return 新添加subNote的id
     */
    String addSubNote(String noteId, SubNote subNote);

    /**
     * 通过noteId获取note
     * @param noteId noteId
     * @return 获取的note
     */
    Note getNoteById(String noteId);

    /**
     * 通过subNoteId和noteId获取subNote
     * @param noteId noteId
     * @param subNoteId subNoteId
     * @return 获取的subNote
     */
    SubNote getSubNoteById(String noteId, String subNoteId);

    /**
     * 通过noteId获取subNote列表
     * @param noteId noteId
     * @return subNote列表
     */
    List<SubNote> getSubNoteListByNoteId(String noteId);

    /**
     * 更新note，不允许通过此方法添加subNote
     * @param noteId 将要更新的noteId
     * @param note 更新的note
     */
    void updateNoteById(String noteId, Note note);

    /**
     * 根据noteId和subNoteId更新subNote,不允许通过此方法添加subNote
     * @param noteId 将要更新的subNote所在的noteId
     * @param subNoteId 将要更新的subNoteId
     * @param subNote 更新的subNote
     */
    void updateSubNoteById(String noteId, String subNoteId, SubNote subNote);

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
     * @param noteId noteId
     * @param subNoteId subNoteId
     */
    void deleteSubNote(String noteId, String subNoteId);

    /**
     * 批量删除subNote
     * @param noteId noteId
     * @param subNoteIdList subNoteId列表
     */
    void deleteSubNoteBatch(String noteId, List<String> subNoteIdList);

    /**
     * 搜索subNote,需指定noteId.  subNoteId可为空,为空时表示在整个note中搜索,不为空时表示在指定subNote中搜索
     * @param noteId noteId
     * @param subNoteId subNoteId
     * @param pattern 匹配模式
     */
    void searchSubNote(String noteId, String subNoteId, String pattern);


}
