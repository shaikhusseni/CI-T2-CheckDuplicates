package com.employee.employee.service;

import com.employee.employee.dtos.EmployeeDto;
import com.employee.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {


    public EmployeeDto saveEmployee(EmployeeDto employeeDto);
    public List <EmployeeDto> getEmployees();
    public EmployeeDto getById(Long empId);


    EmployeeDto updateById(Long empId, Employee updatedEmployee);

    public void deleteById(Long empId);




}
