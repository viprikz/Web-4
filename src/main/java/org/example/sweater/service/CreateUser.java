package org.example.sweater.service;


/*

import org.example.sweater.entitys.Users;
import org.example.sweater.repository.QuestionsRepository;
import org.example.sweater.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUser {
    @Autowired
    private UsersRepository repository;
    private Users user;
    public CreateUser(Users user) {
        this.user= user;
    }
    public Users saveUser(){
        try {
            System.out.println("Началось создание");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(this.user.getPassword());
            this.user.setPassword(hashedPassword);
            System.out.println(repository.findAll());
            repository.save(this.user);
            return repository.save(user);
        } catch (Exception e) {
            System.out.println("Ошибка в создаии пользоваеля");
            System.out.println(e);
        }
        return null;
    }
    public void showUser()
    {
        System.out.println("-------------------------");
        System.out.println(this.user.getUsername());
        System.out.println(this.user.getPassword());
        System.out.println("-------------------------");
    }
}*/
