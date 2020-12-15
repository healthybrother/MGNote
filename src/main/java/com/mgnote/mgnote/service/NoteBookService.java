package com.mgnote.mgnote.service;

import com.mgnote.mgnote.model.NoteBook;

import java.util.List;

public interface NoteBookService {
    /**
     * 添加一个noteBook,同时更新用户信息
     * @param noteBook noteBook
     * @param directoryId 文件夹id
     * @param userId user的id
     * @return noteBook的id
     */
    String addNoteBook(String userId, String directoryId, NoteBook noteBook);

    /**
     * 删除noteBook及其下的note
     * @param noteBookId noteBook的id
     */
    void deleteNoteBook(String noteBookId);

    /**
     *
     * @param noteBookId noteBook的id
     * @return noteBook的id
     */
    NoteBook getNoteBook(String noteBookId);

    /**
     *
     * @param directoryId 文件夹id
     * @return noteBook列表
     */
    List<NoteBook> getNoteBooksByDirectoryId(String directoryId);

    /**
     *
     * @param userId 用户id
     * @return noteBook列表
     */
    List<NoteBook> getNoteBooksByUserId(String userId);

    /**
     *
     * @param userId 用户id
     * @param noteBook 更新的noteBook
     */
    void updateNoteBook(String userId, NoteBook noteBook);
}
