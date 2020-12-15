package com.mgnote.mgnote.service.impl;

import com.google.common.base.Preconditions;
import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.exception.PermissionException;
import com.mgnote.mgnote.model.*;
import com.mgnote.mgnote.repository.*;
import com.mgnote.mgnote.service.NoteBookService;
import com.mgnote.mgnote.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NoteBookServiceImpl implements NoteBookService {

    private NoteRepository noteRepository;
    private UserRepository userRepository;
    private NoteBookRepository noteBookRepository;
    private DirectoryRepository directoryRepository;

    @Autowired
    public NoteBookServiceImpl(NoteRepository noteRepository, UserRepository userRepository, NoteBookRepository noteBookRepository,
                               DirectoryRepository directoryRepository){
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.noteBookRepository = noteBookRepository;
        this.directoryRepository = directoryRepository;
    }

    @Override
    public String addNoteBook(String userId, String directoryId, NoteBook noteBook){
        Preconditions.checkNotNull(userId, "用户id为空");
        Preconditions.checkNotNull(directoryId, "文件夹id为空");

        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Directory> directoryOpt = directoryRepository.findById(directoryId);
        if(!userOpt.isPresent()){
            throw new EntityNotExistException("用户不存在");
        }
        if(!directoryOpt.isPresent()){
            throw new EntityNotExistException("文件夹不存在");
        }

        User user = userOpt.get();
        Directory directory = directoryOpt.get();
        if(!directory.getUserId().equals(userId)){
            throw new PermissionException("无权访问文件夹");
        }

        noteBook.setUserId(userId);
        noteBook.setPrevDirectory(new BriefDirectory(directory));
        noteBook.setPublic(false);
        noteBook.setId(UUID.randomUUID().toString());

        noteBookRepository.save(noteBook);

        return noteBook.getId();
    }

    @Override
    public void deleteNoteBook(String noteBookId) {
        Preconditions.checkNotNull(noteBookId, "笔记本id为空");

        Optional<NoteBook> noteBookOptional = noteBookRepository.findById(noteBookId);
        if(!noteBookOptional.isPresent()){
            throw new EntityNotExistException("笔记本不存在");
        }


        NoteBook noteBook = noteBookOptional.get();

        Optional<Directory> directoryOptional = directoryRepository.findById(noteBook.getPrevDirectory().getId());
        if(!directoryOptional.isPresent()){
            throw new EntityNotExistException("对应文件夹不存在");
        }
        Directory directory = directoryOptional.get();

        List<NoteBook> noteBooks = directory.getNoteBooks();
        noteBooks.remove(noteBook);
        directory.setNoteBooks(noteBooks);
        directoryRepository.save(directory);

        List<BriefNote> notes = noteBook.getNotes();
        List<String> noteIdList = notes.stream().map(BriefNote::getId).collect(Collectors.toList());
        noteRepository.deleteNotesById(noteIdList);

        noteBookRepository.deleteById(noteBookId);
    }

    @Override
    public NoteBook getNoteBook(String noteBookId) {
        Preconditions.checkNotNull(noteBookId, "笔记本id为空");

        Optional<NoteBook> noteBookOptional = noteBookRepository.findById(noteBookId);
        if(noteBookOptional.isPresent())
            return noteBookOptional.get();
        throw new EntityNotExistException("笔记本不存在");
    }

    //TODO:两个分页查找
    @Override
    public List<NoteBook> getNoteBooksByDirectoryId(String directoryId) {
        Preconditions.checkNotNull(directoryId, "笔记本id为空");

        return null;
    }

    @Override
    public List<NoteBook> getNoteBooksByUserId(String userId) {
        Preconditions.checkNotNull(userId, "用户id为空");
        return null;
    }

    @Override
    public void updateNoteBook(String userId, NoteBook noteBook) {
        Preconditions.checkNotNull(userId, "用户id");
        Preconditions.checkNotNull(noteBook, "笔记本为空");

        Optional<NoteBook> beforeOpt = noteBookRepository.findById(noteBook.getId());
        if(!beforeOpt.isPresent())
            throw new EntityNotExistException("笔记本好像已被删除");
        NoteBook before = beforeOpt.get();
        if(!before.getUserId().equals(userId))
            throw new PermissionException("用户权限错误");

        noteBook.setUserId(null);
        NoteBook after = EntityUtil.copyProperties(noteBook, before, true);

        noteBookRepository.save(after);
    }
}
