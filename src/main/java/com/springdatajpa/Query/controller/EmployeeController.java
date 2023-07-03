package com.springdatajpa.Query.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springdatajpa.Query.entity.Employee;
import com.springdatajpa.Query.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	 @Autowired
	    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getEmployeesWithNameStartingWithAAndAgeLessThan25() {
        return employeeRepository.findEmployeesWithNameStartingWithAAndAgeLessThan25();
    }
   
    @GetMapping("/employees/search")
    public List<Employee> searchEmployeesByName(@RequestParam("name") String name)
    {
        return employeeRepository.searchByName(name);
    }
    
    @GetMapping("/searchemployees")
    public List<Employee> searchEmployees(
    		@RequestParam("name") String name,
    		@RequestParam("age") int age,
    		@RequestParam("rollno") int rollno
    		) 
    {
        return employeeRepository.searchEmployees(name, age, rollno);
    }
    
    @PutMapping("/api/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee updatedEmployee) 
    {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) 
        {
            employee.get().setName(updatedEmployee.getName());
            employee.get().setAge(updatedEmployee.getAge());
            employee.get().setRollno(updatedEmployee.getRollno());
            return new ResponseEntity<>(employeeRepository.save(employee.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
