package com.example.employeedetails.Controllers;

import com.example.employeedetails.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employeelist")
public class accountControllerList {

    private List<EmployeeDTO> employeeList = new ArrayList<>();

    /*@GetMapping(path = "/id/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name ="employeeId") Long id){
        for(EmployeeDTO employee : employeeList){
            if(employee.getId() == id){
                return employee;
            }
        }
        return null;
    }*/

    @GetMapping(path = "/id/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name ="employeeId") Long id) {
        return employeeList.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
            return employeeList;
    }


    @PostMapping
    public void addEmployee(@RequestBody EmployeeDTO newEmployee) {
        employeeList.add(newEmployee);
    }

    /*@PutMapping(path = "/id/{employeeId}")
    public void updateEmployee(@PathVariable(name ="employeeId") long id, @RequestBody EmployeeDTO employee){
        int index = 0;
        for(EmployeeDTO tempEmployee : employeeList){
            if(tempEmployee.getId() == id){
                employeeList.set(index, employee);
            }
            index ++;
        }
    }*/

    /*
    * 1. first find emp. with employee id in list.
    * 2. Update that emp. by the help of ID -> data.
     */

    @PutMapping(path = "/id/{employeeId}")
    public void updateEmployee(@PathVariable(name ="employeeId") long id, @RequestBody EmployeeDTO updatedEmployee) {

        employeeList.stream().forEach(tempEmployee -> {
            if(tempEmployee.getId() == id) {
                tempEmployee.setId(updatedEmployee.getId());
                tempEmployee.setFirstName(updatedEmployee.getFirstName());
                tempEmployee.setLastName(updatedEmployee.getLastName());
                tempEmployee.setEmail(updatedEmployee.getEmail());
                tempEmployee.setRole(updatedEmployee.getRole());
                tempEmployee.setDateOfJoining(updatedEmployee.getDateOfJoining());
            }
        });
    }

    @DeleteMapping(path = "/id/{employeeId}")
    public void deleteEmployeeById(@RequestBody EmployeeDTO employee){
        for(EmployeeDTO tempEmployee : employeeList){
            if(tempEmployee.getId() == employee.getId()){
                employeeList.remove(tempEmployee);
            }
        }
    }

}
