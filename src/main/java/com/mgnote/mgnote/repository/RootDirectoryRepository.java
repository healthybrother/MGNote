package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.RootDirectory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RootDirectoryRepository extends MongoRepository<RootDirectory, String> {
}
