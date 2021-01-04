package com.mgnote.mgnote.service;

import com.mgnote.mgnote.model.Directory;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.NoteBook;

import java.util.List;

public interface DirectoryService {

    String addNote(String rootDirectoryId, String noteBookId, Note note);

    String addNoteBook(String rootDirectoryId, String directoryId, NoteBook noteBook);

    String addDirectory(String rootDirectoryId,  String directoryId, Directory directory);

    void updateNoteBook(String rootDirectoryId, String directoryId, String id, NoteBook noteBook);

    void updateDirectory(String rootDirectoryId, String directoryId, String id, Directory directory);

    void getDirectoryById(String rootDirectoryId, String directoryId, String id);

    void getNoteBookById(String rootDirectoryId, String directoryId, String id);

    Directory searchDirectory(String rootDirectoryId, String directoryId, String pattern);

    NoteBook searchNoteBook(String rootDirectoryId, String directoryId, String pattern);

    void deleteNoteBook(String rootDirectory, String directoryId, String id);

    void deleteNoteBookBatch(String rootDirectory, String directoryId, List<String> idList);

    void deleteDirectory(String rootDirectory, String directoryId, String id);

    void deleteDirectoryBatch(String rootDirectory, String directoryId, List<String> idList);
}
