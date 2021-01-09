package com.mgnote.mgnote.controller;

import com.mgnote.mgnote.service.DirectoryService;
import com.mgnote.mgnote.service.NoteService;
import com.mgnote.mgnote.service.ShareNoteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(value = "/directory")
public class DirectoryController {

    private final DirectoryService directoryService;

    @Autowired
    public DirectoryController(DirectoryService directoryService){
        this.directoryService = directoryService;
    }
}
