package com.employee.employee.serviceimpl;

import com.employee.employee.dtos.EmployeeDto;
import com.employee.employee.entity.Employee;
import com.employee.employee.exceptionhandlers.InspireNetException;
import com.employee.employee.repository.EmployeeRepository;
import com.employee.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    //    method for  saving employee in Data Base
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) throws InspireNetException {


// checks the duplicate present or not
        if (employeeRepository.existsByEmpEmail(employeeDto.getEmpEmail())) {
            throw new InspireNetException("Employee with this email  is alreday Exists");

        }
        if (employeeRepository.existsByEmpPhno(employeeDto.getEmpPhno())) {


            throw new InspireNetException("Employee with this phone  is alreday Exists");

        }
        Employee map = modelMapper.map(employeeDto, Employee.class);

        Employee save = employeeRepository.save(map);

        return modelMapper.map(save, EmployeeDto.class);


    }

    //to getAll Employees Data from DB
    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();

        // Mapping each Employee to EmployeeDto using Stream and collect into a new list
        return allEmployees.stream()
                .map(this::empToDto)
                .collect(Collectors.toList());


    }


    //to check get  Single  Employee Data based on empId
    @Override
    public EmployeeDto getById(Long empId) {
        Employee getEmp = employeeRepository.getReferenceById(empId);
        return modelMapper.map(getEmp, EmployeeDto.class);

    }
//    to update existed Employee Data

    @Override
    public EmployeeDto updateById(Long empId, Employee updatedEmployee) throws InspireNetException {
        Employee existingEmployee = employeeRepository.findById(empId).orElse(null);

//         checks employee is present or not  next condition executes
        if (existingEmployee != null) {

//            checks employee Email is unique or not  if it is unique update will done else  throw Exception
            if (updatedEmployee.getEmpEmail() != null &&
                    !updatedEmployee.getEmpEmail().equals(existingEmployee.getEmpEmail()) &&
                    employeeRepository.existsByEmpEmail(updatedEmployee.getEmpEmail())) {
                throw new InspireNetException("Employee with this email already exists");
            }


            //            checks employee phone number  is unique or not  if it is unique update will done else  throw Exception

            if (updatedEmployee.getEmpPhno() != null &&
                    !updatedEmployee.getEmpPhno().equals(existingEmployee.getEmpPhno()) &&
                    employeeRepository.existsByEmpPhno(updatedEmployee.getEmpPhno())) {
                throw new InspireNetException("Employee with this phone number already exists");
            }

            // Update other fields except for email and phone
            if (updatedEmployee.getEmpName() != null) {
                existingEmployee.setEmpName(updatedEmployee.getEmpName());
            }
            if (updatedEmployee.getEmpAddress() != null) {
                existingEmployee.setEmpAddress(updatedEmployee.getEmpAddress());
            }

            // Save the updated employee to DB
            Employee updatedEntity = employeeRepository.save(existingEmployee);

            return modelMapper.map(updatedEntity, EmployeeDto.class);
        } else {
            return null;
        }

    }


//            // Update the existingEmployee object with the fields from updatedEmployee
//            existingEmployee.setEmpName(updatedEmployee.getEmpName());
//
//            if (employeeRepository.existsByEmpEmail(updatedEmployee.getEmpEmail()))
//            {
//                throw new IllegalArgumentException("Employee with this email  is already Exists so,no possible to update");
//
//            }
//            existingEmployee.setEmpPhno(updatedEmployee.getEmpPhno());
//            if (employeeRepository.existsByEmpPhno(updatedEmployee.getEmpPhno())) {
//
//
//                throw new IllegalArgumentException("Employee with this phone  is already Exists  so, no possible to update");
//
//            }
//
//            existingEmployee.setEmpEmail(updatedEmployee.getEmpEmail());
//            existingEmployee.setEmpAddress(updatedEmployee.getEmpAddress());
//
//
//            // Save the updated employee
//            Employee updatedEntity = employeeRepository.save(existingEmployee);
//
//            return modelMapper.map(updatedEntity, EmployeeDto.class);
//        } else {
//            // Handle the case when the employee with the given ID doesn't exist
//            // You can throw an exception or handle it based on your application logic
//            // For example:
//            // throw new NotFoundException("Employee with ID " + empId + " not found");
//            return null;
//
//        }

//    }


    @Override
    public void deleteById(Long empId) {

        employeeRepository.deleteById(empId);
    }

//this is wrong way
//    @Override
//    public EmployeeDto getById(Long empId) {
//
//        Employee map = modelMapper.map(empId, Employee.class);
//
//        Employee getEmp = employeeRepository.getReferenceById(map.getEmpId());
//        return modelMapper.map(getEmp,EmployeeDto.class);
//
//
//
//    }


//
//    @Override
//    public List<EmployeeDto> getEmployees() {
//        List<Employee> all = employeeRepository.findAll();
//        EmployeeDto map = modelMapper.map(all, EmployeeDto.class);
//        return map;
//    }
//
//    @Override
//    public EmployeeDto getById(Long empId) {
//        return employeeRepository.findById(empId).get();
//
//    }
//
//
//    @Override
//    public EmployeeDto updateById(Long empId, Employee updatedEmployee) {
//        return null;
//    }
//
//
//    @Override
//    public EmployeeDto updateById(Long empId, EmployeeDto updatedEmployee) {
//        Employee existingEmployee = employeeRepository.findById(empId).get();
//
//        if (existingEmployee != null) {
//            // Update existingEmployee with details from updatedEmployee
//            // Assuming setters are available in the Employee class
//            existingEmployee.setEmpName(updatedEmployee.getEmpName());
//
//
//            // Save the updated employee to the repository
//            return employeeRepository.save(existingEmployee);
//        }
//
//        return null; // Or handle appropriately if employee not found


    //Dto introduction

//    public Employee (EmployeeDto empDto) {
//        Employee emp = this.modelMapper.map(empDto, Employee.class);
//        return emp;
//
//    }


    public EmployeeDto empToDto(Employee emp) {
        EmployeeDto empDto = this.modelMapper.map(emp, EmployeeDto.class);
        return empDto;
    }

}


