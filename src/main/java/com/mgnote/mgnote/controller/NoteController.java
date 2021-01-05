package com.mgnote.mgnote.controller;

import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.ShareNote;
import com.mgnote.mgnote.model.SubNote;
import com.mgnote.mgnote.model.dto.SearchShareNoteInput;
import com.mgnote.mgnote.service.NoteService;
import com.mgnote.mgnote.util.ListUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api
@RestController
@RequestMapping(value = "/note")
public class NoteController {
    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping("/get/note/{noteId}")
    public ResponseEntity<?> getNoteById(@PathVariable String noteId){
        if(noteId!=null){
            Note note = noteService.getNoteById(noteId);
            return new ResponseEntity<>(note, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add/subNote")
    public ResponseEntity<?> addSubNote(@RequestParam String path, @RequestBody SubNote subNote){
        if(path!=null && subNote != null){
            String id = noteService.addSubNote(path, subNote);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/match/subNote")
    public ResponseEntity<?> matchPathSubNote(@RequestParam String path){
        if(path != null){
            List<SubNote> list = noteService.matchPathSubNotes(path, false);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/matchAll/subNote")
    public ResponseEntity<?> matchAllPathSubNote(@RequestParam String path){
        if(path != null){
            List<SubNote> list = noteService.matchPathSubNotes(path, true);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/subNote/{subNoteId}")
    public ResponseEntity<?> getSubNoteById(@PathVariable String subNoteId){
        if(subNoteId != null){
            SubNote subNote = noteService.getSubNoteById(subNoteId);
            return new ResponseEntity<>(subNote, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/subNote/{subNoteId}")
    public ResponseEntity<?> deleteSubNoteById(@PathVariable String subNoteId){
        if(subNoteId!=null){
            noteService.deleteSubNote(subNoteId);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/delete/note/{noteId}")
    public ResponseEntity<?> deleteNoteById(@PathVariable String noteId){
        if(noteId!=null){
            noteService.deleteNote(noteId);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/update/note/{notebook}/{noteId}")
    public ResponseEntity<?> updateNoteById(@PathVariable String notebook, @PathVariable String noteId, @RequestBody Note note){
        if(notebook!=null && noteId != null && note != null){
            noteService.updateNoteById(notebook, noteId, note);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/subNote/{subNoteId}")
    public ResponseEntity<?> updateSubNoteById(@PathVariable String subNoteId, @RequestBody SubNote subNote){
        if(subNoteId!=null && subNote!=null){
            noteService.updateSubNoteById(subNoteId, subNote);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/search/shareNotes")
    public ResponseEntity<?> searchShareNotes(@RequestBody SearchShareNoteInput input){
        if(input.getShareNote()!=null && input.getListParam()!=null){
            Page<ShareNote> page = noteService.searchShareNotes(input.getShareNote(), input.getListParam());
            Map<String, Object> res = new HashMap<>();
            res.put("shareNotes", page.toList());
            res.put("listParam", ListUtil.getListParamByPage(page));
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
