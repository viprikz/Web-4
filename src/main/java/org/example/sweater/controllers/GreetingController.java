package org.example.sweater.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.sweater.entitys.AnswersE;
import org.example.sweater.entitys.QuestionE;
import org.example.sweater.entitys.Users;
import org.example.sweater.repository.QuestionsRepository;
//import org.example.sweater.service.CreateUser;
//import org.example.sweater.service.CreateUser;
import org.example.sweater.repository.UsersRepository;
import org.example.sweater.service.*;
//import org.graalvm.compiler.lir.LIRInstruction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private Top10 top10;
    @Autowired
    private AddAnswer addAnswer;
    @Autowired
    private Describe describe;
    @Autowired
    private SearchByContent searchByContent;
    @Autowired
    private CreateQuestion createQuestion;

    @RequestMapping(value = "/top10", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> top10() {
        //List<QuestionE> listE = new ArrayList<QuestionE>();
        return new ResponseEntity<String>(top10.show(), HttpStatus.OK);
    }
    @RestController
    @RequestMapping(value = "/post", headers="Content-Type=application/json", method = RequestMethod.POST)
    public class GreetingController10 {
        @Autowired
        private QuestionsRepository repository;

        @Autowired
        private UsersRepository repo;

        @Autowired
        private RegUser regUser;

        @PostMapping(value = "/question/{id}/test", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<String> putAnswerTest(@PathVariable String id, @RequestBody AnswersE answer) throws JSONException, IOException {
            System.out.println(answer.toString());
            List<QuestionE> list = repository.findByIdd(id);
            StringWriter writer = new StringWriter();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, list.get(0));
            return new ResponseEntity<>(writer.toString(), HttpStatus.OK);
        }

        //Добавить ответ
        @PostMapping(value = "/{id}/add/", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<String> putAnswer(@PathVariable String id, @RequestBody AnswersE answer) throws JSONException, IOException {
                return new ResponseEntity<>(addAnswer.show(id, answer), HttpStatus.OK);
        }

        @PostMapping(value = "/register", produces = MediaType.ALL_VALUE)
        public ResponseEntity<String> createUserTest(@RequestBody Users user) throws JSONException, IOException {
            //RegUser regUser = new RegUser();
            regUser.setUser(user);
            String answer = regUser.saveUser();
            return new ResponseEntity<>(answer, HttpStatus.OK);
        }

        @PostMapping(value = "/login", produces = MediaType.ALL_VALUE)
        public ResponseEntity<String> login(@RequestBody Users user) throws JSONException, IOException {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            try {
                if (encoder.matches(user.getPassword(), repo.findByUsername(user.getUsername()).getPassword())) {
                    return new ResponseEntity<>("Success", HttpStatus.OK);
                }
            } catch (Exception e) {
                System.out.println(e);
                return new ResponseEntity<>("Error no user", HttpStatus.OK);
            }
            return new ResponseEntity<>("Error no user", HttpStatus.OK);
        }
        @PostMapping(value = "/create", produces = MediaType.ALL_VALUE)
        public ResponseEntity<String> createQuestion(@RequestBody QuestionE questionE) throws JSONException, IOException {
            return new ResponseEntity<>(createQuestion.show(questionE), HttpStatus.OK);
        }
    }
    //Поиск вопроса по Id
    @RequestMapping(value="/{id}", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findQuestion(@PathVariable String id) throws JSONException, IOException {
        return new ResponseEntity<>(describe.show(id), HttpStatus.OK);

    }
    //Поиск вопроса по Заголовку

    @RequestMapping(value="/search/{content}", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findQuestionByContent(@PathVariable String content) throws JSONException, IOException {
        return new ResponseEntity<>(searchByContent.show(content), HttpStatus.OK);
    }

}
