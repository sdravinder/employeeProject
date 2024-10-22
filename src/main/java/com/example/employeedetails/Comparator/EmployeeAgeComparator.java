package com.example.employeedetails.Comparator;

import com.example.employeedetails.dto.EmployeeDTO;

import java.util.Comparator;

public class EmployeeAgeComparator implements Comparator<EmployeeDTO> {

    @Override
    public int compare(EmployeeDTO o1, EmployeeDTO o2) {
        return (int)(o1.getAge() - o2.getAge());
    }

}