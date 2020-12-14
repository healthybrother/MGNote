package com.mgnote.mgnote.service;

import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.NoteContent;

import java.util.List;

public interface NoteService {

    /**
     * 在笔记本中添加新的笔记
     * @param userId 用户id
     * @param noteBookId 笔记本id
     * @param note 笔记信息
     * @param noteContent 笔记特容
     * @return 生成的笔记id（UUID）
     */
    String addNote(String userId, String noteBookId, Note note, NoteContent noteContent);

    /**
     * 在笔记下面添加子笔记
     * @param noteId 父笔记id
     * @param note 笔记信息
     * @param noteContent 笔记内容
     * @return 生成的笔记id（UUID）
     */
    String addSubNote(String noteId, Note note, NoteContent noteContent);

    /**
     * 根据笔记id获取笔记信息
     * @param noteId 笔记信息的id
     * @return 笔记信息（不包括内容）
     * @throws EntityNotExistException 符合id的note不存在时抛出异常
     */
    Note getNoteById(String noteId) throws EntityNotExistException;

    /**
     * 根据笔记id更新笔记信息
     * @param noteId 笔记信息的id
     * @param note 笔记的信息
     */
    void updateNoteInfoById(String noteId, Note note);

    /**
     * 根据笔记信息的id进行软删除（设置deleted）
     * @param noteId 笔记信息的id
     */
    void deleteNoteSoft(String noteId);

    /**
     * 根据笔记信息的id进行删除
     * @param noteId 笔记信息的id
     */
    void deleteNote(String noteId);

    /**
     * 根据笔记内容id跟新笔记内容
     * @param noteContentId 笔记内容id
     * @param noteContent 笔记内容
     */
    void updateNoteContentById(String noteContentId, NoteContent noteContent);

    /**
     * 根据id更新笔记信息以及笔记内容
     * @param id 笔记id
     * @param noteInfo 笔记信息
     * @param noteContent 笔记内容
     */
    void updateNoteById(String id, Note noteInfo, NoteContent noteContent);
}
