package org.example.employeepayroll.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
    @Entity
    @Table(name = "users")
    public class AuthUser {

        String firstName;
        String lastName;
        String email;
        String password;
        String hashPass;
        String token;

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;

        public AuthUser(String firstName, String lastName, String email, String password, String hashPass) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.hashPass = hashPass;

            this.token="";
            this.id = null;
        }
    }

