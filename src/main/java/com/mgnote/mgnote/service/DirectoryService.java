package com.mgnote.mgnote.service;

import com.mgnote.mgnote.model.Directory;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.NoteBook;
import com.mgnote.mgnote.model.RootDirectory;
import com.mgnote.mgnote.model.dto.BriefNote;

import java.util.Date;
import java.util.List;

public interface DirectoryService {


    /**
     * 在指定位置添加note
     * @param path 路径,内容为id,用'/'分隔,首位为'/',第一个id为根文件夹id
     * @param note 添加的note
     * @return 新添加note的briefNote
     */
    BriefNote addNote(String path, Note note);

    /**
     * 在指定位置添加noteBook
     * @param path 路径,内容为id,用'/'分隔,首位为'/',第一个id为根文件夹id
     * @param noteBook 添加的noteBook
     * @return 新添加noteBook的id
     */
    String addNoteBook(String path, NoteBook noteBook);

    /**
     * 在指定位置添加directory
     * @param path 路径,内容为id,用'/'分隔,首位为'/',第一个id为根文件夹id
     * @param directory 新添加directory的id
     * @return 新增文件夹的id
     */
    String addDirectory(String path, Directory directory);

    /**
     * 更新noteBook,不允许用于删除
     * @param path 路径,内容为id,用'/'分隔,首位为'/',第一个id为根文件夹id
     * @param id noteBook的id
     * @param noteBook 更新的noteBook
     */
    void updateNoteBook(String path, String id, NoteBook noteBook);

    /**
     * 更新文件夹,不允许用于删除
     * @param path 路径,内容为id,用'/'分隔,首位为'/',第一个id为根文件夹id
     * @param id directory的id
     * @param directory 更新的directory
     */
    void updateDirectory(String path, String id, Directory directory);

    /**
     * 更新笔记信息(主要是名字)
     * @param path 路径,内容为id,用'/'分隔,首位为'/',第一个id为根文件夹id
     * @param briefNote briefNote
     */
    void updateNoteInfo(String path, BriefNote briefNote);

    /**
     * 通过userId获取根文件夹
     * @param userId userId
     * @return 获取的RootDirectory
     */
    RootDirectory getRootDirectoryByUserId(String userId);

    /**
     * 通过id获取根文件夹
     * @param id 根文件夹的id
     * @return 获取的根文件夹
     */
    RootDirectory getRootDirectoryById(String id);

    /**
     * 通过id和路径获取directory
     * @param path 路径,内容为id,用'/'分隔,首位为'/',第一个id为根文件夹id
     * @param id directory的id
     * @return 获取的文件夹
     */
    Directory getDirectoryById(String path, String id);

    /**
     * 通过id和路径获取noteBook
     * @param path 路径,内容为id,用'/'分隔,首位为'/',第一个id为根文件夹id
     * @param id noteBook的id
     * @return 获取的noteBook
     */
    NoteBook getNoteBookById(String path, String id);

    /**
     * 删除noteBook
     * @param path 路径,内容为id,用'/'分隔,首位为'/',第一个id为根文件夹id
     * @param id noteBook的id
     */
    void deleteNoteBook(String path, String id);

    /**
     * 删除directory
     * @param path 路径,内容为id,用'/'分隔,首位为'/',第一个id为根文件夹id
     * @param id directory的id
     */
    void deleteDirectory(String path, String id);
}
