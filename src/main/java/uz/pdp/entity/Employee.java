package uz.pdp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import uz.pdp.dto.EmployeeDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private Boolean gender;
    private Double salary;
    private String email;
    private String phoneNumber;

    public Employee(EmployeeDto employee) {
        this.name = employee.name();
        this.surname = employee.surname();
        this.age = employee.age();
        this.gender = employee.gender();
        this.email = employee.email();
        this.phoneNumber = employee.phoneNumber();
    }
}
