package org.example.employeepayroll.controller;

import org.example.employeepayroll.dto.EmployeeDTO;
import org.example.employeepayroll.interfaces.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/employeepayrollservice")
    @Slf4j
    public class EmployeeController {

        IEmployeeService iEmployeeService;
        //UC1 --> CRUD operations on employee database through REST API's
        @GetMapping("/get/{id}")
        public EmployeeDTO get(@Valid@PathVariable Long id) {
            log.info("Get employee by id: {}" + id);
            return iEmployeeService.get(id);
        }

        @PostMapping("/create")
        public EmployeeDTO create(@Valid @RequestBody EmployeeDTO newEmp) {
            log.info("Employee tried to create with body: {}", getJSON(newEmp));
            return iEmployeeService.create(newEmp);
        }

        @PutMapping("/edit/{id}")
        public EmployeeDTO edit(@Valid @RequestBody EmployeeDTO emp,@Valid @PathVariable Long id) {
            log.info("Employee tried to edit with id : {} and body : {}", id, getJSON(emp));
            return iEmployeeService.edit(emp, id);
        }

        @DeleteMapping("/delete/{id}")
        public String delete(@Valid@PathVariable Long id){
            log.info("Employee tried to delete with id: {}", id);
            return iEmployeeService.delete(id);
        }

        @GetMapping("/clear")
        public String clear(){

            log.info("Database clear request is made");
            return iEmployeeService.clear();

        }

        public String getJSON(Object object) {
            try {
                ObjectMapper obj = new ObjectMapper();
                return obj.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                log.error("Reason : {} Exception : {}", "Conversion error from Java Object to JSON");
            }
            return null;
        }

        }
