package com.employee.employee.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity

@Table(name = "Employee")
public class Employee {
    @Column(name = "EMP_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;




    @Column(name = "EMP_NAME")
    private String empName;
    @Column(name = "EMP_EMAIL")
    private String empEmail;


    @Column(name = "EMP_PHNO")
    private String empPhno;
    @Column(name = "EMP_ADDRESS")
    private String empAddress;


}


