package org.example.sweater.controllers;

import org.example.sweater.entitys.AnswersE;
import org.example.sweater.entitys.QuestionE;
import org.example.sweater.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    private QuestionsRepository repository;
    @GetMapping("/top5")
    public String greeting(Model model) {
        try{
            List<QuestionE> list =repository.findAll();
            int z = 0;
            if(list.size() >=10)
                z = 9;
            list.subList(list.size() - z , list.size());
            System.out.println(list);
            model.addAttribute("name", list);
        }catch (Exception e){ System.out.println(e);}
        return "greeting";
    }
    //Поиск вопроса по Id
    /*@RequestMapping(value="/findId/{id}", method= RequestMethod.GET)
    public String findQuestion(@PathVariable String id, Model  model) {
        List<QuestionE> list = repository.findByShortcontent(id);
        model.addAttribute("name",  list.get(0));
        return "greeting";


    }*/
    //Поиск вопроса по Заголовку
    @RequestMapping(value="/search/{content}", method= RequestMethod.GET)
    public String findQuestionByContent(@PathVariable String content, Model  model) {
        List<QuestionE> list = repository.findByShortcontent(content);
        //System.out.println(content);
        //System.out.println(list);
        try{model.addAttribute("name",  list.get(0));
        }catch (Exception e){ System.out.println(e);}
        return "greeting";
    }

    //Поиск вопроса по id
    @RequestMapping(value="/question/{id}", method= RequestMethod.GET)
    public String openQuestion(@PathVariable String id, Model  model) {
       List<QuestionE> list = repository.findByIdd(id);
       try {
           model.addAttribute("name", list.get(0));
       }catch (Exception e){ System.out.println(e);}
        return "greeting";
    }

    //Весь вопрос
        @RequestMapping(value="/question/{id}/ans/{content}", method= RequestMethod.GET)
    public String putAnswer(@PathVariable String id, @PathVariable String content,  Model  model) {
        List<QuestionE> list = repository.findByIdd(id);
        AnswersE Ans = new AnswersE(content);
        System.out.println(content);
        System.out.println(Ans);
        //list.get(0).addAnswer(Ans);
        //System.out.println(list.get(0));
        try {
            list.get(0).addAnswer(Ans);
            repository.save(list.get(0));
            model.addAttribute("name", list.get(0));
        }catch (Exception e){ System.out.println(e);}
        return "greeting";
    }
    /*@RequestMapping Mapping("/listHeaders")
    public ResponseEntity<String> listAllHeaders(
            @RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            LOG.info(String.format("Header '%s' = %s", key, value));
        });

        return new ResponseEntity<String>(
                String.format("Listed %d headers", headers.size()), HttpStatus.OK);
    }
    @RestController
    @RequestMapping("users")
    public class UserController {

        @PostMapping
        public UserRest createUser(@RequestBody AnswersE requestUserDetails) {
            UserRest returnValue = new UserRest();

            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(requestUserDetails, userDto);
            UserDto createdUser = userService.createUser(userDto);
            BeanUtils.copyProperties(createdUser, returnValue);
            return returnValue;
        }
    }*/

}

