package com.mgnote.mgnote.util;

import com.mgnote.mgnote.model.Note;
import com.mgnote.mgnote.model.SubNote;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class ExampleUtil {
    public static Example<Note> getNoteExample(Note note, Boolean isPublic){
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withIgnoreCase();
        return Example.of(note, exampleMatcher);
    }

    public static Example<SubNote> getSubNoteExample(SubNote subNote){
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withIgnoreCase();
        return Example.of(subNote, exampleMatcher);
    }
}
