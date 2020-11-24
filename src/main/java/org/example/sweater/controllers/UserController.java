package org.example.sweater.controllers;
/*
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sweater.entitys.Users;
import org.example.sweater.repository.QuestionsRepository;
import org.example.sweater.service.CreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;

@RestController
@RequestMapping("/registration")
public class UserController {
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody Users requestUser) {
        CreateUser createUser = new CreateUser(requestUser);
        createUser.showUser();
        createUser.saveUser();
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}*/