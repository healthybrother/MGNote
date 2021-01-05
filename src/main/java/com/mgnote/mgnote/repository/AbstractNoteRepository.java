package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.AbstractNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstractNoteRepository extends MongoRepository<AbstractNote, String> {
}
