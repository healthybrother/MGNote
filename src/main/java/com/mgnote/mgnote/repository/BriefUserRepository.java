package com.mgnote.mgnote.repository;

import com.mgnote.mgnote.model.BriefNote;
import com.mgnote.mgnote.model.BriefUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BriefUserRepository extends MongoRepository<BriefUser, String> {
}
