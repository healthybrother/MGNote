package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.SubNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubNoteRepository extends MongoRepository<SubNote, String> {
}
