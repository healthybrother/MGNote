package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.BriefNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BriefNoteRepository extends MongoRepository<BriefNote, String> {
}
