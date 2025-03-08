package org.example.employeepayroll.services;
import org.example.employeepayroll.dto.EmployeeDTO;
import org.example.employeepayroll.entities.EmployeeEntity;
import org.example.employeepayroll.interfaces.IEmployeeService;
import org.example.employeepayroll.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

    @Service
    @Slf4j
    public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDTO get(Long id){
        try{
            EmployeeEntity empFound = employeeRepository.findById(id).orElseThrow(()->
            {
                return new RuntimeException();
            });
            EmployeeDTO empDto = new EmployeeDTO(empFound.getName(), empFound.getSalary());
            empDto.setId(empFound.getId());
            log.info("Employee DTO send for id: {} is : {}", id, getJSON(empDto));

            return empDto;
        }
        catch(RuntimeException e){
            log.error("Exception : {} due to : {} ", e, "Cannot find employee with given id");
        }
        return null;
    }

        public EmployeeDTO create(EmployeeDTO newEmp){

            EmployeeEntity newEntity = new EmployeeEntity(newEmp.getName(), newEmp.getSalary());

            employeeRepository.save(newEntity);
            log.info("Employee saved in db: {}", getJSON(newEntity));

            EmployeeDTO emp = new EmployeeDTO(newEntity.getName(), newEntity.getSalary());

            emp.setId(newEntity.getId());
            log.info("Employee DTO sent: {}", getJSON(emp));

            return emp;
        }

        public EmployeeDTO edit(EmployeeDTO emp, Long id) {
            //finding employee
            try {
                EmployeeEntity foundEmp = employeeRepository.findById(id).orElseThrow(() ->
                {
                    log.error("Cannot find employee with id : {}", id);
                    return new RuntimeException("cannot find employee with given id");
                });

                //updating details
                foundEmp.setName(emp.getName());
                foundEmp.setSalary(emp.getSalary());

                //saving in database
                employeeRepository.save(foundEmp);
                log.info("Employee saved after editing in db is : {}", getJSON(foundEmp));

                //creating dto to return
                EmployeeDTO employeeDTO = new EmployeeDTO(foundEmp.getName(), foundEmp.getSalary());
                employeeDTO.setId(foundEmp.getId());


                return employeeDTO;

            } catch (RuntimeException e) {
                log.error("Exception : {} due to : {} \", e, \"cannot find employee with given id");
            }
            return null;
        }
        public String delete(Long id){

            try {
                EmployeeEntity foundEmp = employeeRepository.findById(id).orElseThrow(() ->
                {
                    return new RuntimeException();
                });

                employeeRepository.delete(foundEmp);

                return "Employee Deleted";
            }
            catch(RuntimeException e){
                log.error("Exception : {} Reason : Cannot find user with id : {}", e, id);
            }
            return null;
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
        public String clear(){
        employeeRepository.deleteAll();
        log.info("all Employees data cleared");
        return "All Employees data cleared";
        }

}
