package com.avanit.journalApp.controller;
import com.avanit.journalApp.entity.User;
import com.avanit.journalApp.service.MailService;
import com.avanit.journalApp.service.UserDetailsServiceImpl;
import com.avanit.journalApp.service.UserService;
import com.avanit.journalApp.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private MailService mailService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        userService.saveNewUser(user);

        String subject = "Welcome to Journal App ";
        String message = "Hi " + user.getUsername() + ",\n\n" +
                "Thank you for signing up to Journal App! 🎉\n\n" +
                "You can now start writing your personal journals, track your thoughts, and reflect on your day.\n\n" +
                "➡️ Visit your journal dashboard to get started.\n\n" +
                "Happy writing!\n" +
                "- The Journal App Team";

        mailService.sendMail(user.getEmail(), subject, message);

        return ResponseEntity.ok("User registered and email sent.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}