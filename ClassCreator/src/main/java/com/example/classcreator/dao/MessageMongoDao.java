package com.example.classcreator.dao;

import com.example.classcreator.model.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageMongoDao extends MongoRepository<Character, UUID> {
    List<Character> findByOwnerLogin(String ownerLogin);
}
