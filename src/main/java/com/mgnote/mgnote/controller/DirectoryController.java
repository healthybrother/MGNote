package com.mgnote.mgnote.controller;

import com.mgnote.mgnote.model.Directory;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.NoteBook;
import com.mgnote.mgnote.service.DirectoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api
@CrossOrigin
@RestController
@RequestMapping(value = "/directory")
public class DirectoryController {

    private final DirectoryService directoryService;

    @Autowired
    public DirectoryController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{userId}")
    public ResponseEntity<?> getRootDirectory(@PathVariable String userId) {
        return new ResponseEntity<>(directoryService.getRootDirectoryByUserId(userId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/noteBook/{noteBookId}")
    public ResponseEntity<?> getNoteBookById(@PathVariable String noteBookId, @RequestParam String path) {
        return new ResponseEntity<>(directoryService.getNoteBookById(path, noteBookId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/directory/{directoryId}")
    public ResponseEntity<?> getDirectoryById(@PathVariable String directoryId, @RequestParam String path) {
        return new ResponseEntity<>(directoryService.getDirectoryById(path, directoryId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/noteBook")
    public ResponseEntity<?> addNoteBook(@RequestBody NoteBook noteBook, @RequestParam String path) {
        return new ResponseEntity<>(directoryService.addNoteBook(path, noteBook), HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/directory")
    public ResponseEntity<?> addDirectory(@RequestBody Directory directory, @RequestParam String path) {
        return new ResponseEntity<>(directoryService.addDirectory(path, directory), HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/note")
    public ResponseEntity<?> addNote(@RequestBody Note note, @RequestParam String path) {
        return new ResponseEntity<>(directoryService.addNote(path, note), HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/directory/{directoryId}")
    public ResponseEntity<?> updateDirectory(@RequestBody Directory directory, @PathVariable String directoryId, @RequestParam String path) {
        directoryService.updateDirectory(path, directoryId, directory);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/noteBook/{noteBookId}")
    public ResponseEntity<?> updateNoteBook(@RequestBody NoteBook noteBook, @PathVariable String noteBookId, @RequestParam String path) {
        directoryService.updateNoteBook(path, noteBookId, noteBook);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/noteBook/{noteBookId}")
    public ResponseEntity<?> deleteNoteBook(@PathVariable String noteBookId, @RequestParam String path) {
        directoryService.deleteNoteBook(path, noteBookId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/directory/{directoryId}")
    public ResponseEntity<?> deleteDirectory(@PathVariable String directoryId, @RequestParam String path) {
        directoryService.deleteDirectory(path, directoryId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
