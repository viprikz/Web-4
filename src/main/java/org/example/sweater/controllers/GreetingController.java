package org.example.sweater.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sweater.entitys.AnswersE;
import org.example.sweater.entitys.QuestionE;
import org.example.sweater.entitys.Users;
import org.example.sweater.repository.QuestionsRepository;
//import org.example.sweater.service.CreateUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    private QuestionsRepository repository;
    @RequestMapping(value = "/top10", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> top10(Model model) {
        //List<QuestionE> listE = new ArrayList<QuestionE>();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        /*Sort sort = new Sort(Sort.Direction.ASC, "id");
        List<QuestionE> students = repository.findStudent(1, sort);*/
        try{
            List<QuestionE> list =repository.findAll();
            mapper.writeValue(writer, list);
            int z = 0;
            if(list.size() >=10)
                z = 9;
            list.subList(list.size() - z , list.size());
            //model.addAttribute("name", list);
            //model.addAttribute("name", jsonArray.toString().substring(1,jsonArray.toString().length() - 1));
        }catch (Exception e){ System.out.println(e);}
        return new ResponseEntity<>(writer.toString(), HttpStatus.OK);
    }
    @RestController
    @RequestMapping(value = "/post", headers="Content-Type=application/json", method = RequestMethod.POST)
    public class GreetingController10 {
        @Autowired
        private QuestionsRepository repository;

        @PostMapping(value="/question/{id}/test", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<String> putAnswerTest(@PathVariable String id,  @RequestBody AnswersE answer) throws JSONException, IOException {
            System.out.println(answer.toString());
            List<QuestionE> list = repository.findByIdd(id);
            StringWriter writer = new StringWriter();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, list.get(0));
            return new ResponseEntity<>(writer.toString(), HttpStatus.OK);
        }
        //Добавить ответ
            @PostMapping(value="/findById/{id}/add/",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<String> putAnswer(@PathVariable String id,  @RequestBody AnswersE answer) throws JSONException, IOException {
            List<QuestionE> list = repository.findByIdd(id);
            AnswersE Ans = answer;
            StringWriter writer = new StringWriter();
            ObjectMapper mapper = new ObjectMapper();
            QuestionE saved = new QuestionE();
            try {
                list.get(0).addAnswer(Ans);
                saved = repository.save(list.get(0));

            }catch (Exception e){
                System.out.println(e);
                mapper.writeValue(writer, saved);
                return new ResponseEntity<>(writer.toString(), HttpStatus.OK);
            }
            mapper =  new ObjectMapper();
            mapper.writeValue(writer, saved);
            System.out.println(writer.toString());
            if(saved == list.get(0))
                return new ResponseEntity<>(writer.toString(), HttpStatus.OK);
            else {
                return new ResponseEntity<>(writer.toString(), HttpStatus.OK);
            }
        }

    }
    //Поиск вопроса по Id
    @RequestMapping(value="/findById/{id}", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findQuestion(@PathVariable String id) throws JSONException, IOException {
        List<QuestionE> list = repository.findByIdd(id);
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer,list.get(0));
        return new ResponseEntity<>(writer.toString(), HttpStatus.OK);


    }

    //Поиск вопроса по Заголовку

    @RequestMapping(value="/search/{content}", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findQuestionByContent(@PathVariable String content, Model  model) throws JSONException, IOException {
        List<QuestionE> list = repository.findByShortcontent(content);
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(writer, list.get(0));
        }catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(writer.toString(), HttpStatus.OK);
    }

    /*@RestController
    @RequestMapping("/registration")
    public class UserController {
        @Autowired
        private QuestionsRepository repository;
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
}
