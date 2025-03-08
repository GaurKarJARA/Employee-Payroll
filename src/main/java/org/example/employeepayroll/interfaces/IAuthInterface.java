package org.example.employeepayroll.interfaces;

 import org.example.employeepayroll.dto.AuthUserDTO;
 import org.example.employeepayroll.dto.LoginDTO;
 import org.example.employeepayroll.dto.PassDTO;
 import org.springframework.stereotype.Service;

    @Service
    public interface IAuthInterface {

        public String register(AuthUserDTO user) ;

        public String login(LoginDTO user);

        public AuthUserDTO forgotPassword(PassDTO pass, String email) ;

        public String resetPassword(String email, String currentPass, String newPass);

        public String clear();
    }

