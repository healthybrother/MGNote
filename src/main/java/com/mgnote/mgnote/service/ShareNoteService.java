package com.mgnote.mgnote.service;

import com.mgnote.mgnote.model.BriefUser;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.ShareNote;
import com.mgnote.mgnote.model.SubNote;
import com.mgnote.mgnote.model.dto.ListParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ShareNoteService {

    String addShareNote(String noteId, String description);

    String addShareSubNote(String subNoteId, String description);

    void closeShareNote(String userId, String id);

    void deleteShareNote(String userId, String id);

    String commentShareNote(BriefUser briefUser, String shareNoteId, String content);

    void deleteComment(BriefUser briefUser, String shareNoteId, String id);

    Page<ShareNote> searchShareNotes(ShareNote shareNote, ListParam listParam);

    List<ShareNote> findAllByUserId(String userId);
}
