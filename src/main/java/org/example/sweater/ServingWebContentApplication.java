package org.example.sweater;
import org.example.sweater.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class ServingWebContentApplication {

    @Autowired
    private QuestionsRepository repository;
    private Object QuestionE;

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }
}