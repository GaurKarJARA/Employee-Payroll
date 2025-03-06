package org.example.employeepayroll.interfaces;

import org.example.employeepayroll.dto.EmployeeDTO;
import org.example.employeepayroll.entities.EmployeeEntity;
import org.springframework.stereotype.Service;

@Service
public interface IEmployeeService {
    public EmployeeDTO get(Long id);

    public EmployeeDTO create(EmployeeDTO newEmp);

    public EmployeeDTO edit(EmployeeDTO emp, Long id);

    public String delete(Long id);


}