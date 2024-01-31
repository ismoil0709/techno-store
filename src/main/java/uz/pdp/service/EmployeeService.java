package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.dto.EmployeeDto;
import uz.pdp.entity.Employee;

import java.util.List;

@Service
public interface EmployeeService {
    void save(EmployeeDto employee);
    void update(Employee employee);
    void delete(Long id);
    Employee getById(Long id);
    List<Employee> getAll();
    Employee getByEmail(String email);
    Employee getByPhoneNumber(String phoneNumber);
    List<Employee> getByName(String name);
    List<Employee> getByAge(Integer age);
    List<Employee> getByGender(Boolean gender);
    List<Employee> getBySalary(Double salary);
}
