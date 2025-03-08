package org.example.employeepayroll.controller;

import org.example.employeepayroll.dto.*;
import org.example.employeepayroll.interfaces.IAuthInterface;
import org.example.employeepayroll.services.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j

public class UserController {


    IAuthInterface iAuthInterface;

    @Autowired
    EmailService emailService;

    @PostMapping(path = "/register")
    public String register(@Valid @RequestBody AuthUserDTO user){
        log.info("Employee tried to register with body: {}", getJSON(user));
        return iAuthInterface.register(user);
    }

    //UC> For User Login
    @PostMapping(path ="/login")
    public String login(@Valid @RequestBody LoginDTO user){
        log.info("Employee tried to login with body: {}", getJSON(user));
        return iAuthInterface.login(user);
    }

    //UC> For sending mail to another person
    @PostMapping(path = "/sendMail")
    public String sendMail(@Valid @RequestBody MailDTO message) {
        log.info("Employee tried to send email with body: {}", getJSON(message));
        return "Mail sent";
    }

    //UC> Added forgot password functionality
    @PutMapping("/forgotPassword/{email}")
    public AuthUserDTO forgotPassword(@Valid @RequestBody PassDTO pass, @Valid @PathVariable String email){
        log.info("Employee applied for forgot password with body: {}", getJSON(pass));
        return iAuthInterface.forgotPassword(pass, email);
    }

    //> Added reset password functionality
    @PutMapping("/resetPassword/{email}")
    public String resetPassword(@PathVariable @Valid String email ,@RequestParam @Valid String currentPass, @RequestParam @Valid String newPass){
        log.info("Employee applied for forgot password with email: {}", email);
        return iAuthInterface.resetPassword(email, currentPass, newPass);
    }

    @GetMapping("/clear")
    public String clear(){

        log.info("Database clear request is made");
        return iAuthInterface.clear();

    }

    public String getJSON(Object object){
        try {
            ObjectMapper obj = new ObjectMapper();
            return obj.writeValueAsString(object);
        }
        catch(JsonProcessingException e){
            log.error("Reason : {} Exception : {}", "Conversion error from Java Object to JSON");
        }
        return null;
    }

}