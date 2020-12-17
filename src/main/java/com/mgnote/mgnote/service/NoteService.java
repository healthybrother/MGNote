package com.mgnote.mgnote.service;

import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.NoteContent;
import com.mgnote.mgnote.model.dto.GetNoteOutput;
import com.mgnote.mgnote.model.dto.ListPage;
import com.mgnote.mgnote.model.dto.ListParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoteService {

    /**
     * 在笔记本中添加新的笔记
     * @param userId 用户id
     * @param noteBookId 笔记本id
     * @param note 笔记信息
     * @param noteContents 笔记内容列表
     * @return 生成的笔记id（UUID）
     */
    String addNote(String userId, String noteBookId, Note note, List<String> noteContents);

    /**
     * 在笔记下面添加子笔记
     * @param noteId 父笔记id
     * @param note 笔记信息
     * @param noteContents 笔记内容列表
     * @return 生成的笔记id（UUID）
     */
    String addSubNote(String noteId, Note note, List<String> noteContents);

    /**
     * 根据笔记id获取笔记信息
     * @param noteId 笔记信息的id
     * @return 笔记信息（不包括内容）
     * @throws EntityNotExistException 符合id的note不存在时抛出异常
     */
    Note getNoteInfoById(String noteId) throws EntityNotExistException;

    /**
     * 根据id获取笔记信息以及内容
     * @param id 笔记信息以及笔记内容的id
     * @return 笔记（包括信息以及内容）
     */
    GetNoteOutput getNoteById(String id);

    /**
     * 根据id列表获取笔记信息列表
     * @param ids 笔记信息id列表
     * @return 笔记信息列表
     */
    List<Note> getNotesById(List<String> ids);

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
     * 根据id更新笔记信息以及笔记内容
     * @param id 笔记id
     * @param note 笔记信息
     */
    void updateNoteById(String id, Note note);

    /**
     * 公开笔记
     * @param noteId 笔记id
     * @param isPublic 是否公开
     */
    void setNotePublic(String noteId, Boolean isPublic);

    /**
     * 根据id列表批删除笔记（硬删除）
     * @param ids 笔记id列表
     */
    void deleteNoteBatch(List<String> ids);

    /**
     * 模糊搜索并分页查询笔记信息
     * @param note 模糊搜索需要匹配的信息
     * @param listParam 分页参数
     * @param isPublic 是否公开
     * @return 分页后的笔记信息
     */
    ListPage<Note> searchNoteInfo(Note note, ListParam listParam, Boolean isPublic);
}
