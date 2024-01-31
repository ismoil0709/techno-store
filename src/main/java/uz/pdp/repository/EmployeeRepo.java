package uz.pdp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee,Long>{
    Employee getEmployeeByEmail(String email);
    Employee getEmployeeByPhoneNumber(String phoneNumber);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getEmployeesByAge(Integer age);
    List<Employee> getEmployeesByGender(Boolean gender);
    List<Employee> getEmployeesBySalary(Double salary);
}
