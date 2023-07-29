package com.varsha.coworkermanagementservice.repository;

import com.varsha.coworkermanagementservice.entity.Coworker;
import org.bson.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoworkerRepository extends MongoRepository<Coworker,String> {
}
