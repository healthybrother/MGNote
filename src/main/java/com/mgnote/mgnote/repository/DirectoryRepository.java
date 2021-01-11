package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.RootDirectory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DirectoryRepository extends MongoRepository<RootDirectory, String> {
}
