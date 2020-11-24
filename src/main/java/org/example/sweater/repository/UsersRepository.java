package org.example.sweater.repository;


import org.example.sweater.entitys.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {
    Users findByUsername(String username);
}