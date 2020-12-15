package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.NoteContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteContentRepository extends MongoRepository<NoteContent, String> {
    List<NoteContent> findAllByIdIn(List<String> ids);
}
