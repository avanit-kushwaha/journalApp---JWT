package com.avanit.journalApp.service;

import com.avanit.journalApp.entity.User;
import com.avanit.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    public void saveNewUser(User userEntry){

        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userRepository.save(userEntry);
    }
    public void saveUser(User user){


        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }


    public User findByUserName(String username){
        return userRepository.findByUsername(username);
    }
}
