package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.BriefDirectory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BriefDirectoryRepository extends MongoRepository<BriefDirectory, String> {
}
