package com.mgnote.mgnote.service.impl;

import com.mgnote.mgnote.model.Directory;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.NoteBook;
import com.mgnote.mgnote.service.DirectoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectoryServiceImpl implements DirectoryService {
    @Override
    public String addNote(String rootDirectoryId, String noteBookId, Note note) {
        return null;
    }

    @Override
    public String addNoteBook(String rootDirectoryId, String directoryId, NoteBook noteBook) {
        return null;
    }

    @Override
    public String addDirectory(String rootDirectoryId, String directoryId, Directory directory) {
        return null;
    }

    @Override
    public void updateNoteBook(String rootDirectoryId, String directoryId, String id, NoteBook noteBook) {

    }

    @Override
    public void updateDirectory(String rootDirectoryId, String directoryId, String id, Directory directory) {

    }

    @Override
    public void getDirectoryById(String rootDirectoryId, String directoryId, String id) {

    }

    @Override
    public void getNoteBookById(String rootDirectoryId, String directoryId, String id) {

    }

    @Override
    public Directory searchDirectory(String rootDirectoryId, String directoryId, String pattern) {
        return null;
    }

    @Override
    public NoteBook searchNoteBook(String rootDirectoryId, String directoryId, String pattern) {
        return null;
    }

    @Override
    public void deleteNoteBook(String rootDirectory, String directoryId, String id) {

    }

    @Override
    public void deleteNoteBookBatch(String rootDirectory, String directoryId, List<String> idList) {

    }

    @Override
    public void deleteDirectory(String rootDirectory, String directoryId, String id) {

    }

    @Override
    public void deleteDirectoryBatch(String rootDirectory, String directoryId, List<String> idList) {

    }
}
