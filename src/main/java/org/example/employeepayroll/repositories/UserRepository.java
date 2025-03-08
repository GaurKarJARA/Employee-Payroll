package org.example.employeepayroll.repositories;


 import org.example.employeepayroll.entities.AuthUser;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;


    @Repository
    public interface UserRepository extends JpaRepository<AuthUser, Long> {

        public AuthUser findByEmail(String email);

    }

