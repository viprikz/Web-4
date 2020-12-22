package org.example.sweater.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sweater.entitys.AnswersE;
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
public class AddAnswer {
    @Autowired
    private QuestionsRepository repository;
    private QuestionE questionE;

    public String show(String id, AnswersE answer) throws IOException {
        List<QuestionE> list = repository.findByIdd(id);
        AnswersE Ans = answer;
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        QuestionE saved = new QuestionE();
        try {
            list.get(0).addAnswer(Ans);
            saved = repository.save(list.get(0));
        } catch (Exception e) {
            System.out.println(e);
            mapper.writeValue(writer, saved);
            return writer.toString();
        }
        mapper = new ObjectMapper();
        mapper.writeValue(writer, saved);
        System.out.println(writer.toString());
        if (saved == list.get(0))
            return writer.toString();
        else {
            return writer.toString();
        }
    }
}