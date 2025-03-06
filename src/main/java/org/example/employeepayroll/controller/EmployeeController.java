package org.example.employeepayroll.controller;

import org.example.employeepayroll.dto.EmployeeDTO;
import org.example.employeepayroll.interfaces.IEmployeeService;
import org.example.employeepayroll.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/employeepayrollservice")
    public class EmployeeController {
        @Autowired
        IEmployeeService iEmployeeService;
        //UC1 --> CRUD operations on employee database through REST API's
        @GetMapping("/get/{id}")
        public EmployeeDTO get(@PathVariable Long id){
            return iEmployeeService.get(id);
        }

        @PostMapping("/create")
        public EmployeeDTO create(@RequestBody EmployeeDTO newEmp){
            return iEmployeeService.create(newEmp);
        }

        @PutMapping("/edit/{id}")
        public EmployeeDTO edit(@RequestBody EmployeeDTO emp, @PathVariable Long id){
            return iEmployeeService.edit(emp, id);
        }

        @DeleteMapping("/delete/{id}")
        public String delete(@PathVariable Long id){
            return iEmployeeService.delete(id);
        }


}
