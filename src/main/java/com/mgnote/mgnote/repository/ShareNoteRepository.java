package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.ShareNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ShareNoteRepository extends MongoRepository<ShareNote, String> {
    List<ShareNote> findAllById(String id);
}
