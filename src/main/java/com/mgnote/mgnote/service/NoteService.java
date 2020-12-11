package com.mgnote.mgnote.service;

import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.Note;

import java.util.List;

public interface NoteService {

    /**
     * 添加新的笔记
     * @param note 笔记信息
     * @return 生成的笔记id（UUID）
     */
    String addNote(Note note);

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
    void updateNoteBy(String noteId, Note note);

    /**
     * 根据笔记信息的id进行软删除（设置deleted）
     * @param noteId 笔记信息的id
     */
    void deleteNoteSoft(String noteId);
}
