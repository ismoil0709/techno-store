package uz.pdp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.EmployeeDto;
import uz.pdp.entity.Employee;
import uz.pdp.repository.EmployeeRepo;
import uz.pdp.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Override
    public void save(EmployeeDto employee) {
        if (employee.name() == null || employee.name().isEmpty() || employee.name().isBlank())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (employee.surname() == null || employee.surname().isEmpty() || employee.surname().isBlank())
            throw new IllegalArgumentException("Surname cannot be null or empty");
        if (employee.age() == null)
            throw new IllegalArgumentException("Age cannot be null");
        if (employee.gender() == null)
            throw new IllegalArgumentException("Gender cannot be null");
        if (employee.email() == null || employee.email().isEmpty() || employee.email().isBlank())
            throw new IllegalArgumentException("Email cannot be null or empty");
        if (employee.phoneNumber() == null || employee.phoneNumber().isEmpty() || employee.phoneNumber().isBlank())
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        employeeRepo.save(new Employee(employee));
    }

    @Override
    public void update(Employee employee) {
        if (employee == null )
            throw new IllegalArgumentException("Employee cannot be null");
        if (employee.getId() == null)
            throw new IllegalArgumentException("Employee ID cannot be null");
        Optional<Employee> byId = employeeRepo.findById(employee.getId());
        if (byId.isEmpty())
            throw new IllegalArgumentException("Employee not found");
        Employee employee1 = byId.get();
        employeeRepo.save(
        Employee.builder()
                .id(employee.getId())
                .name(Objects.requireNonNullElse(employee.getName(),employee1.getName()))
                .surname(Objects.requireNonNullElse(employee.getSurname(),employee1.getSurname()))
                .age(Objects.requireNonNullElse(employee.getAge(),employee1.getAge()))
                .gender(Objects.requireNonNullElse(employee.getGender(),employee1.getGender()))
                .salary(Objects.requireNonNullElse(employee.getSalary(),employee1.getSalary()))
                .email(Objects.requireNonNullElse(employee.getEmail(),employee1.getEmail()))
                .phoneNumber(Objects.requireNonNullElse(employee.getPhoneNumber(),employee1.getPhoneNumber()))
                .build()
        );
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID cannot be null");
        Optional<Employee> byId = employeeRepo.findById(id);
        if (byId.isEmpty())
            throw new IllegalArgumentException("Employee not found");
        employeeRepo.delete(byId.get());
    }

    @Override
    public Employee getById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID cannot be null");
        Optional<Employee> byId = employeeRepo.findById(id);
        if (byId.isEmpty())
            throw new IllegalArgumentException("Employee not found");
        return byId.get();
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        employeeRepo.findAll().iterator().forEachRemaining(employees::add);
        if (employees.isEmpty())
            throw new IllegalArgumentException("Employees is empty");
        return employees;
    }

    @Override
    public Employee getByEmail(String email) {
        if (email == null || email.isEmpty() || email.isBlank())
            throw new IllegalArgumentException("Email cannot be null or empty");
        Employee employeeByEmail = employeeRepo.getEmployeeByEmail(email);
        if (employeeByEmail == null)
            throw new IllegalArgumentException("Employee not found");
        return employeeByEmail;
    }

    @Override
    public Employee getByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.isBlank())
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        Employee employeeByPhoneNumber = employeeRepo.getEmployeeByPhoneNumber(phoneNumber);
        if (employeeByPhoneNumber == null)
            throw new IllegalArgumentException("Employee not found");
        return employeeByPhoneNumber;
    }

    @Override
    public List<Employee> getByName(String name) {
        if (name == null || name.isEmpty() || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or empty");
        List<Employee> employeesByName = employeeRepo.getEmployeesByName(name);
        if (employeesByName.isEmpty())
            throw new IllegalArgumentException("Employees not found");
        return employeesByName;
    }

    @Override
    public List<Employee> getByAge(Integer age) {
        if (age == null)
            throw new IllegalArgumentException("Age cannot be null");
        List<Employee> employeesByAge = employeeRepo.getEmployeesByAge(age);
        if (employeesByAge.isEmpty())
            throw new IllegalArgumentException("Employees not found");
        return employeesByAge;
    }

    @Override
    public List<Employee> getByGender(Boolean gender) {
        if (gender == null)
            throw new IllegalArgumentException("Gender cannot be null");
        List<Employee> employeesByGender = employeeRepo.getEmployeesByGender(gender);
        if (employeesByGender.isEmpty())
            throw new IllegalArgumentException("Employees not found");
        return employeesByGender;
    }

    @Override
    public List<Employee> getBySalary(Double salary) {
        if (salary == null)
            throw new IllegalArgumentException("Salary cannot be null");
        List<Employee> employeesBySalary = employeeRepo.getEmployeesBySalary(salary);
        if (employeesBySalary.isEmpty())
            throw new IllegalArgumentException("Employees not found");
        return employeesBySalary;
    }
}
