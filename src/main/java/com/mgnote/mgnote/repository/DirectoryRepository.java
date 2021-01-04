package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.RootDirectory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DirectoryRepository extends MongoRepository<RootDirectory, String> {
}
