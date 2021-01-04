package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.ShareNote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShareNoteRepository extends MongoRepository<ShareNote, String> {
}
