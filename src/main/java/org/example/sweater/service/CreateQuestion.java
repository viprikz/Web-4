package org.example.sweater.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sweater.entitys.QuestionE;
import org.example.sweater.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;
@Service
public class CreateQuestion{
    @Autowired
    private QuestionsRepository repository;
    private QuestionE questionE;

    public String show(QuestionE questionE) {
        if(questionE.getShortcontent() == "" || questionE.getContent() == "")
            return "Bad Format";
        try {
            repository.save(questionE);
        }catch (Exception e)
        {
            System.out.println(e);
            return "Error";
        }
        return "Success";
    }
}

