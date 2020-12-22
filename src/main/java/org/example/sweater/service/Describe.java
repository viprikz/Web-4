package org.example.sweater.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sweater.entitys.QuestionE;
import org.example.sweater.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Service
public class Describe {
    @Autowired
    private QuestionsRepository repository;
    private QuestionE questionE;

    public String show(String id) throws IOException {
        List<QuestionE> list = repository.findByIdd(id);
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        if(list.size() != 0) {
            mapper.writeValue(writer, list.get(0));
            return writer.toString();
        }else
            return "Not Found";
    }
}