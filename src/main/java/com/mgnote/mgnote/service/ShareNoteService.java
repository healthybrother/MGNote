package com.mgnote.mgnote.service;

import com.mgnote.mgnote.model.BriefUser;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.SubNote;

public interface ShareNoteService {

    String addShareNote(BriefUser briefUser, Note note);

    String addShareNote(BriefUser briefUser, SubNote subNote);

    void closeShareNote(String userId, String id);

    void deleteShareNote(String userID, String id);

    void commentShareNote(BriefUser briefUser, String shareNoteId, String content);

    void deleteComment(String shareNoteId, String id);
}
