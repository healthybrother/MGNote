package com.mgnote.mgnote.controller;

import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.service.NoteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping(value = "/note")
public class NoteController {
    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNote(@RequestBody Note note){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/note/{noteId}")
    public ResponseEntity<?> getNoteById(@PathVariable String noteId){
        if(noteId!=null){
            Note note = noteService.getNoteById(noteId);
            return new ResponseEntity<>(note, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
