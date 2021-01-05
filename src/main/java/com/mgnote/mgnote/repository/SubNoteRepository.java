package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.SubNote;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubNoteRepository extends MongoRepository<SubNote, String> {
    List<SubNote> findAllByIdIn(List<String> id);

    List<SubNote> findAllByPathRegex(String regex);

    void deleteAllByIdIn(List<String> id);
}
