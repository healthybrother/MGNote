package com.mgnote.mgnote.service.impl;

import com.google.common.base.Preconditions;
import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.exception.PermissionException;
import com.mgnote.mgnote.model.*;
import com.mgnote.mgnote.model.dto.ListParam;
import com.mgnote.mgnote.repository.NoteRepository;
import com.mgnote.mgnote.repository.ShareNoteRepository;
import com.mgnote.mgnote.repository.SubNoteRepository;
import com.mgnote.mgnote.repository.UserRepository;
import com.mgnote.mgnote.service.ShareNoteService;
import com.mgnote.mgnote.util.ExampleUtil;
import com.mgnote.mgnote.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

//TODO：添加token身份认证
@Service
public class ShareNoteServiceImpl implements ShareNoteService {
    private final ShareNoteRepository shareNoteRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final SubNoteRepository subNoteRepository;

    @Autowired
    public ShareNoteServiceImpl(ShareNoteRepository shareNoteRepository, NoteRepository noteRepository, UserRepository userRepository, SubNoteRepository subNoteRepository) {
        this.shareNoteRepository = shareNoteRepository;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.subNoteRepository = subNoteRepository;
    }

    @Override
    public String addShareNote(String noteId, String description) {
        Preconditions.checkNotNull(noteId, "未输入笔记id");
        Optional<Note> noteOptional = noteRepository.findById(noteId);
        if(noteOptional.isPresent()){
            Note note = noteOptional.get();
            Optional<User> userOptional = userRepository.findById(note.getUserId());
            if(userOptional.isPresent()){
                String id = UUID.randomUUID().toString();
                User user = userOptional.get();
                ShareNote shareNote = new ShareNote(user, note);
                shareNote.setDescribe(description);
                shareNote.setId(id);
                shareNoteRepository.save(shareNote);
                return id;
            }
            else throw new EntityNotExistException("用户不存在");
        }
        else throw new EntityNotExistException("笔记不存在");
    }

    @Override
    public String addShareSubNote(String subNoteId, String description) {
        Preconditions.checkNotNull(subNoteId, "未输入子笔记id");
        Optional<SubNote> noteOptional = subNoteRepository.findById(subNoteId);
        if(noteOptional.isPresent()){
            SubNote note = noteOptional.get();
            Optional<User> userOptional = userRepository.findById(note.getUserId());
            if(userOptional.isPresent()){
                String id = UUID.randomUUID().toString();
                User user = userOptional.get();
                ShareNote shareNote = new ShareNote(user, note);
                shareNote.setDescribe(description);
                shareNote.setId(id);
                shareNoteRepository.save(shareNote);
                return id;
            }
            else throw new EntityNotExistException("用户不存在");
        }
        else throw new EntityNotExistException("笔记不存在");
    }

    @Override
    public void closeShareNote(String userId, String id) {
        Preconditions.checkNotNull(userId, "未输入用户id");
        Preconditions.checkNotNull(id, "未输入分享记录id");
        Optional<ShareNote> opt = shareNoteRepository.findById(id);
        if(opt.isPresent()){
            ShareNote shareNote = opt.get();
            if(shareNote.getUserId().equals(userId)){
                shareNote.setDel(true);
            }
            else throw new PermissionException("无操作权限");
        }
        else throw new EntityNotExistException("分享记录不存在");
    }

    @Override
    public void deleteShareNote(String userId, String id) {
        Preconditions.checkNotNull(userId, "未输入用户id");
        Preconditions.checkNotNull(id, "未输入分享记录id");
        Optional<ShareNote> opt = shareNoteRepository.findById(id);
        if(opt.isPresent()){
            ShareNote shareNote = opt.get();
            if(shareNote.getUserId().equals(userId)){
                shareNoteRepository.delete(shareNote);
            }
            else throw new PermissionException("无操作权限");
        }
        else throw new EntityNotExistException("分享记录不存在");
    }

    @Override
    public String commentShareNote(BriefUser briefUser, String shareNoteId, String content) {
        Preconditions.checkNotNull(briefUser, "未输入用户信息");
        Preconditions.checkNotNull(shareNoteId, "未输入分享笔记id");
        Preconditions.checkNotNull(content, "未输入评论内容");
        Optional<ShareNote> opt = shareNoteRepository.findById(shareNoteId);
        if(opt.isPresent()){
            String id = UUID.randomUUID().toString();
            ShareNote shareNote = opt.get();
            Comment comment = new Comment(briefUser);
            comment.setContent(content);
            comment.setId(id);
            shareNote.getCommentList().add(comment);
            shareNoteRepository.save(shareNote);
            return id;
        }
        else throw new EntityNotExistException("分享笔记不存在");
    }

    @Override
    public void deleteComment(BriefUser briefUser, String shareNoteId, String id) {
        Preconditions.checkNotNull(briefUser, "未输入用户信息");
        Preconditions.checkNotNull(shareNoteId, "未输入分享笔记id");
        Preconditions.checkNotNull(id, "未输入评论id");
        Optional<ShareNote> opt = shareNoteRepository.findById(shareNoteId);
        if(opt.isPresent()){
            ShareNote shareNote = opt.get();
            List<Comment> comments = shareNote.getCommentList();
//            for(Iterator<Comment> iterator=comments.listIterator();iterator.hasNext();){
//                Comment comment = iterator.next();
//                if(comment.getId().equals(id)){
//                    iterator.remove();
//                    break;
//                }
//            }
            comments = comments.stream().filter(comment -> !id.equals(comment.getId())).collect(Collectors.toList());
            shareNote.setCommentList(comments);
            shareNoteRepository.save(shareNote);
        }
        else throw new EntityNotExistException("分享笔记不存在");
    }

    @Override
    public Page<ShareNote> searchShareNotes(ShareNote shareNote, ListParam listParam) {
        Preconditions.checkNotNull(shareNote, "未输入筛选信息");
        Preconditions.checkNotNull(listParam, "未输入分页信息");
        Example<ShareNote> example = ExampleUtil.getShareNoteExample(shareNote);
        return shareNoteRepository.findAll(example, ListUtil.getPageableByListParam(listParam));
    }

    @Override
    public List<ShareNote> findAllByUserId(String userId) {
        Preconditions.checkNotNull(userId, "未输入用户id");
        return shareNoteRepository.findAllById(userId);
    }
}
