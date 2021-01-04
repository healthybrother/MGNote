package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {
}
