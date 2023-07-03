package com.springdatajpa.Query.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springdatajpa.Query.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    @Query("SELECT e FROM Employee e WHERE e.name LIKE 'A%' AND e.age < 25")
    List<Employee> findEmployeesWithNameStartingWithAAndAgeLessThan25();

    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    List<Employee> searchByName(String name);
    
    @Query("SELECT e FROM Employee e WHERE e.name = :name AND e.age = :age AND e.rollno = :rollno")
    List<Employee> searchEmployees(String name, int age, int rollno);
    
}
