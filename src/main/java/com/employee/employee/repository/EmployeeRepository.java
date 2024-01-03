package com.employee.employee.repository;

import com.employee.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository  extends JpaRepository<Employee ,Long> {



}
