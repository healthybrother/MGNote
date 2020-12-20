package com.mgnote.mgnote.util;

import com.mgnote.mgnote.model.Note;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class ExampleUtil {
    public static Example<Note> getNoteExample(Note note, Boolean isPublic){
        note.setDeleted(false);
        note.setPublic(isPublic);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withIgnoreCase();
        return Example.of(note, exampleMatcher);
    }
}
