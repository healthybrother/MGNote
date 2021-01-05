package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.ShareNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareNoteRepository extends MongoRepository<ShareNote, String> {
}
