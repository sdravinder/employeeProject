package com.example.employeedetails.Controllers;

import com.example.employeedetails.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/employeeSet")
public class accountControllerSet {

    private Set<EmployeeDTO> employeeSet = new HashSet();

    @GetMapping("/id/{employeeId}")
    public Set<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        for (EmployeeDTO employeeDTO : employeeSet) {
            if (employeeDTO.getId() == id) {
                return employeeSet;
            }
        }
        return null;
    }

    @GetMapping
    public Set<EmployeeDTO> getAllEmployees() {
        return employeeSet;
    }

    @PostMapping
    public void addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeSet.add(employeeDTO);
    }

    @PutMapping("/id/{employeeId}")
    public void updateEmployee(@PathVariable int employeeId, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO existingEmployee = null;
        for (EmployeeDTO emp : employeeSet) {
            if (existingEmployee != null) {
                employeeSet.remove(existingEmployee);
                employeeSet.add(employeeDTO);
            }
        }
    }

    @DeleteMapping("/id/{employeeId}")
    public void removeEmployee(@PathVariable(name = "employeeId") Long id) {
        employeeSet.remove(id);
    }

}
