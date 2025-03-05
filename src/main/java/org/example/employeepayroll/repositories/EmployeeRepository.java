package org.example.employeepayroll.repositories;
import org.example.employeepayroll.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    }
