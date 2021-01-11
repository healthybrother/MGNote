package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface NoteRepository extends MongoRepository<Note, String> {
    void deleteAllByIdIn(List<String> list);
}
