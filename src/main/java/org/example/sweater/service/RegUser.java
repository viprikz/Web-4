package org.example.sweater.service;

import org.example.sweater.entitys.Users;
import org.example.sweater.repository.QuestionsRepository;
import org.example.sweater.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegUser {
    @Autowired
    private UsersRepository repo;
    private Users user;

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return this.user;
    }

    public String saveUser() {
        try {
            repo.findByUsername(this.getUser().getUsername());
            if (repo.findByUsername(this.getUser().getUsername()) == null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(this.user.getPassword());
                this.user.setPassword(hashedPassword);
                this.user = repo.save(user);
                return "Success";
            } else {
                return "Already exist";
            }
        } catch (Exception e) {
            System.out.println("Ошибка в создани пользоваеля");
            System.out.println(e);
        }
        return "Error";
    }

    public void showUser() {
        System.out.println("-------------------------");
        System.out.println(this.user.getUsername());
        System.out.println(this.user.getPassword());
        System.out.println("-------------------------");
    }
}
