package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The Interface NoteRepository.
 *
 * @author Danushka Jayamaha
 */
@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findAllByIdIn(List<String> ids);
}