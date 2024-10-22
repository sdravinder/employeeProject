package com.example.employeedetails.Comparator;

import com.example.employeedetails.dto.EmployeeDTO;

import java.util.Comparator;

public class EmployeeCityComparator  implements Comparator<EmployeeDTO> {

    @Override
    public int compare(EmployeeDTO o1, EmployeeDTO o2) {
        return o1.getCity().compareTo(o2.getCity());
    }
}
