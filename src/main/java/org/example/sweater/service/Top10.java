package org.example.sweater.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sweater.entitys.QuestionE;
import org.example.sweater.entitys.Users;
import org.example.sweater.repository.QuestionsRepository;
import org.example.sweater.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;

@Service
public class Top10 {
    @Autowired
    private QuestionsRepository repository;
    private QuestionE questionE;

    public String show() {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try{
            List<QuestionE> list =repository.findAll();
            mapper.writeValue(writer, list);
            int z = 0;
            if(list.size() >=10)
                z = 9;
            list.subList(list.size() - z , list.size());
        }catch (Exception e){ System.out.println(e);}
        return writer.toString();
    }
}