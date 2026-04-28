package com.vcube.app04.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data

@Entity
@Table(name = "employee6")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;
    @NotBlank(message = "Name is required")
    private String ename;
    private int age;
    
    private Double salary;
    @NotNull(message = "Salary is required")
    private String department;
    @NotBlank(message = "Role is required")
    private String role;
    @Email(message = "Invalid email format")
    private String email;
    private Long phoneNumber;

    private String address;
    
    @CreationTimestamp

    private LocalDate joiningDate;
}
