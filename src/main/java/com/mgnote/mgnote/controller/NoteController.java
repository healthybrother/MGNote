package com.mgnote.mgnote.controller;

import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.dto.ResponseValue;
import com.mgnote.mgnote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/note")
public class NoteController {
    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseValue<String> addNote(@RequestBody Note note){
        if(note!=null){
            String id = noteService.addNote(note);
            return ResponseValue.success(id);
        }
        return ResponseValue.fail(null);
    }

    @ResponseBody
    @RequestMapping(value = "/get/{noteId}", method = RequestMethod.GET)
    public ResponseValue<Note> getNoteById(@PathVariable String noteId) throws EntityNotExistException {
        Note note = noteService.getNoteById(noteId);
        return ResponseValue.success(note);
    }
}
