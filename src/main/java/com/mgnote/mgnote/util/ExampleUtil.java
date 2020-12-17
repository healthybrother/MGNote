package com.mgnote.mgnote.util;

import com.mgnote.mgnote.model.Note;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class ExampleUtil {
    public static Example<Note> getNoteExample(Note note, Boolean isPublic){
        note.setDeleted(false);
        note.setPublic(isPublic);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("topic", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("isPublic", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("userInfo", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("prevNoteBook", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("prevNote", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("userInfo", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("createdAt", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
                .withMatcher("updatedAt", ExampleMatcher.GenericPropertyMatchers.ignoreCase());
        return Example.of(note, exampleMatcher);
    }
}
