package org.example.employeepayroll.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class EmployeeDTO {

    @NotBlank
    @Pattern(regexp = "^[A-Z][a-zA-Z]{2,}( [A-Z][a-zA-Z]{2,})*$")
    String name;
    Long salary;

    Long id;

    public EmployeeDTO(String name, Long salary) {
        this.name = name;
        this.salary = salary;

        this.id = null;
    }

}