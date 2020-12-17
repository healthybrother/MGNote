package com.mgnote.mgnote.service;

import com.mgnote.mgnote.model.NoteContent;

import java.util.List;

public interface NoteContentService {
    /**
     * 根据笔记内容id获取笔记内容
     * @param noteContentId 笔记内容id
     * @param exist 是否需要判断相应的note是否存在
     * @return 笔记内容
     */
    NoteContent getNoteContentById(String noteContentId, Boolean exist);

    /**
     * 根据id列表获取笔记内容列表
     * @param ids 笔记内容id列表
     * @return 笔记内容列表
     */
    List<NoteContent> getNoteContentsById(List<String> ids);

    /**
     * 添加笔记内容
     * @param noteContents 笔记内容列表
     * @return 生成的笔记内容id列表
     */
    List<String> addNoteContents(List<NoteContent> noteContents);

    /**
     * 根据笔记内容id跟新笔记内容
     * @param noteContentId 笔记内容id
     * @param noteContent 笔记内容
     */
    void updateNoteContentById(String noteContentId, NoteContent noteContent);

    /**
     * 批量更新笔记内容
     * @param noteContents 笔记内容列表
     */
    void updateNoteContentsById(List<NoteContent> noteContents);
}
