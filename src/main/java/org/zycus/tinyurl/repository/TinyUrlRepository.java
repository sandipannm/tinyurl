package org.zycus.tinyurl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.zycus.tinyurl.model.TinyUrlModel;

@Repository
public interface TinyUrlRepository extends MongoRepository<TinyUrlModel, String>
{

}
