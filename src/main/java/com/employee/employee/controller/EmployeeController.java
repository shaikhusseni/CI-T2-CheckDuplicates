package com.employee.employee.controller;



import com.employee.employee.dtos.EmployeeDto;
import com.employee.employee.entity.Employee;
import com.employee.employee.repository.EmployeeRepository;
import com.employee.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping("/getAll")
    public List<EmployeeDto> findAll()
    {
         return  employeeService.getEmployees();
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employee) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

@GetMapping("/getby/{empId}")
public EmployeeDto getById(@PathVariable("empId") Long empId) {
    return employeeService.getById(empId);
}
    @DeleteMapping("/deleteby/{empId}")
    public void deleteEmployeeById(@PathVariable("empId") Long empId) {
        employeeService.deleteById(empId);
    }


    @PutMapping("/updateby/{empId}")
    public EmployeeDto updateEmployeeById(@PathVariable("empId") Long empId, @RequestBody Employee updatedEmployee) {
        return employeeService.updateById(empId, updatedEmployee);
    }





    //    @GetMapping("/getAll")
//    public List<EmployeeDto> getEmployees() {
//
//        List<EmployeeDto> employees = employeeService.getEmployees();
//        return employees;
//    }
//
//

    //    @PostMapping("/save")
//    public Employee saveEmployee(@RequestBody Employee employee) {
//        return employeeService.saveEmployee(employee);
//    }


//    @PutMapping("/update")
//
//    public Employee updateById(Employee employee, Long empId) {
//
//
//        return employeeService.updateById(employee, empId);
//    }

//
//    @PutMapping("/{empId}")
//    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable Long empId, @RequestBody Employee updatedEmployee) {
//        employeeService.updateById(empId, updatedEmployee)
//
//        if (updated != null) {
//            return ResponseEntity.ok(updated);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


}