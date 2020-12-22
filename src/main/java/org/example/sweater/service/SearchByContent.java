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
public class SearchByContent {
    @Autowired
    private QuestionsRepository repository;
    private QuestionE questionE;

    public String show(String content) {
        List<QuestionE> list = repository.findByShortcontent(content);
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        if(list.size() == 0)
            return "Empty";
        try {
            mapper.writeValue(writer, list.get(0));
        }catch (Exception e) {
            System.out.println(e);
        }
        return writer.toString();
    }
}

