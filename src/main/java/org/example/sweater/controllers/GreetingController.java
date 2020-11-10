package org.example.sweater.controllers;

import com.mongodb.util.JSON;
import org.example.sweater.entitys.AnswersE;
import org.example.sweater.entitys.QuestionE;
import org.example.sweater.QuestionsRepository;
import org.example.sweater.entitys.Test;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    private QuestionsRepository repository;
    @GetMapping("/top10")
    public String greeting(Model model) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try{
            List<QuestionE> list =repository.findAll();
            int z = 0;
            if(list.size() >=10)
                z = 9;
            list.subList(list.size() - z , list.size());
            //model.addAttribute("name", list);
            for(int i = 0; i < list.size(); i++) {
                jsonObject = new JSONObject();
                jsonObject.put("content", list.get(i).getContent());
                jsonObject.put("id", list.get(i).getid());
                jsonObject.put("order", list.get(i).getOrder());
                jsonObject.put("shortcontent", list.get(i).getShortcontent());
                jsonArray.put(jsonObject);
            }
            //model.addAttribute("name", jsonArray.toString().substring(1,jsonArray.toString().length() - 1));

        }catch (Exception e){ System.out.println(e);}

        return jsonArray.toString().substring(1,jsonArray.toString().length() - 1);
    }
    /*@Controller
    public class GreetingController10 {
        @Autowired
        private QuestionsRepository repository;

        @GetMapping("/top11")
        public String greeting10(@RequestBody Test str) {
            try {
                List<QuestionE> list = repository.findAll();
                int z = 0;
                if (list.size() >= 10)
                    z = 9;
                list.subList(list.size() - z, list.size());
                System.out.println(list);
                //model.addAttribute("name", str);
            } catch (Exception e) {
                System.out.println(e);
            }
            //model.addAttribute("name", str);
            return str.toString();
        }
    }*/
    //Поиск вопроса по Id
    @RequestMapping(value="/findId/{id}", method= RequestMethod.GET)
    public String findQuestion(@PathVariable String id, Model  model) throws JSONException {
        List<QuestionE> list = repository.findByShortcontent(id);
        model.addAttribute("name",  list.get(0));
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for(int i = 0; i < list.size(); i++) {
            jsonObject = new JSONObject();
            jsonObject.put("content", list.get(i).getContent());
            jsonObject.put("id", list.get(i).getid());
            jsonObject.put("order", list.get(i).getOrder());
            jsonObject.put("shortcontent", list.get(i).getShortcontent());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString().substring(1,jsonArray.toString().length() - 1);


    }
    //Поиск вопроса по Заголовку
    @RequestMapping(value="/search/{content}", method= RequestMethod.GET)
    public String findQuestionByContent(@PathVariable String content, Model  model) throws JSONException {
        List<QuestionE> list = repository.findByShortcontent(content);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for(int i = 0; i < list.size(); i++) {
            jsonObject = new JSONObject();
            jsonObject.put("content", list.get(i).getContent());
            jsonObject.put("id", list.get(i).getid());
            jsonObject.put("order", list.get(i).getOrder());
            jsonObject.put("shortcontent", list.get(i).getShortcontent());
            jsonArray.put(jsonObject);
        }
        //model.addAttribute("name", jsonArray.toString().substring(1,jsonArray.toString().length() - 1));
        try{model.addAttribute("name", jsonArray.toString().substring(1,jsonArray.toString().length() - 1));
        }catch (Exception e){ System.out.println(e);}
        return jsonArray.toString().substring(1,jsonArray.toString().length() - 1);
    }

    //Поиск вопроса по id
    @RequestMapping(value="/question/{id}", method= RequestMethod.GET)
    public String openQuestion(@PathVariable String id, Model  model) throws JSONException {
       List<QuestionE> list = repository.findByIdd(id);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for(int i = 0; i < list.size(); i++) {
            jsonObject = new JSONObject();
            jsonObject.put("content", list.get(i).getContent());
            jsonObject.put("id", list.get(i).getid());
            jsonObject.put("order", list.get(i).getOrder());
            jsonObject.put("shortcontent", list.get(i).getShortcontent());
            jsonArray.put(jsonObject);
        }
        //model.addAttribute("name", jsonArray.toString().substring(1,jsonArray.toString().length() - 1));
       try {
           model.addAttribute("name", jsonArray.toString().substring(1,jsonArray.toString().length() - 1));
       }catch (Exception e){ System.out.println(e);}
        return jsonArray.toString().substring(1,jsonArray.toString().length() - 1);
    }

    //Добавить ответ
        @RequestMapping(value="/question/{id}/add/{content}", method= RequestMethod.GET)
    public String putAnswer(@PathVariable String id, @PathVariable String content,  Model  model) throws JSONException {
        List<QuestionE> list = repository.findByIdd(id);
        AnswersE Ans = new AnswersE(content);
        System.out.println(content);
        System.out.println(Ans);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for(int i = 0; i < list.size(); i++) {
            jsonObject = new JSONObject();
            jsonObject.put("content", list.get(i).getContent());
            jsonObject.put("id", list.get(i).getid());
            jsonObject.put("order", list.get(i).getOrder());
            jsonObject.put("shortcontent", list.get(i).getShortcontent());
            jsonArray.put(jsonObject);
        }
        //list.get(0).addAnswer(Ans);
        //System.out.println(list.get(0));
        try {
            list.get(0).addAnswer(Ans);
            repository.save(list.get(0));
        }catch (Exception e){ System.out.println(e);}
        return jsonArray.toString().substring(1,jsonArray.toString().length() - 1);
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

