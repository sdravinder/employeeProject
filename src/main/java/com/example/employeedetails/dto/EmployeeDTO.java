package com.example.employeedetails.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Comparable<EmployeeDTO> {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private String role;
    private Double salary;
    private String city;

    @Override
    public int compareTo(EmployeeDTO o) {
        return this.age - o.age;
    }

}

// 1. Comparator class for ->  ID, firstName, age, dateOfJoining.
// 2. Comparator class for -> ID + firstName

