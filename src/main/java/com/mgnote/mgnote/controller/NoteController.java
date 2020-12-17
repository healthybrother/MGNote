package com.mgnote.mgnote.controller;

import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.NoteContent;
import com.mgnote.mgnote.model.dto.AddNoteInput;
import com.mgnote.mgnote.service.NoteContentService;
import com.mgnote.mgnote.service.NoteService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(value = "/note")
public class NoteController {
    private NoteService noteService;
    private NoteContentService noteContentService;

    @Autowired
    public NoteController(NoteService noteService, NoteContentService noteContentService) {
        this.noteService = noteService;
        this.noteContentService = noteContentService;
    }

    @ApiOperation(value = "/note/add", notes = "创建笔记", response = String.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully Created"),
        @ApiResponse(code = 400, message = "Invalid Request")})
    public HttpEntity<?> addNote(@ApiParam(value = "笔记信息", required = true) @Validated @RequestBody AddNoteInput input){
        if(input!=null){
            String id;
            List<String> ids = noteContentService.addNoteContents(input.getNoteContents());
            if(input.getUserId()!=null) {
                id = noteService.addNote(input.getUserId(), input.getId(), input.getNote(), ids);
            }
            else{
                id = noteService.addSubNote(input.getId(), input.getNote(), ids);
            }
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/get/{noteId}", method = RequestMethod.GET)
    public ResponseEntity<?> getNoteById(@PathVariable String noteId) throws EntityNotExistException {
        Note note = noteService.getNoteInfoById(noteId);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @GetMapping(value = "/get/content{contentId}")
    public ResponseEntity<?> getContentById(@PathVariable String contentId){
        NoteContent noteContent = noteContentService.getNoteContentById(contentId);
        return new ResponseEntity<>(noteContent, HttpStatus.OK);
    }
}
