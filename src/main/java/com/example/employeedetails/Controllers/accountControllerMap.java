package com.example.employeedetails.Controllers;

import com.example.employeedetails.Comparator.*;
import com.example.employeedetails.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employeeMap")
public class accountControllerMap {

    private Map<Long, EmployeeDTO> employeeMap = new HashMap<>();

    /*@GetMapping(path = "/id/{employeeId}")
    public Map<Long, EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        for (EmployeeDTO employee : employeeMap.values()) {
            if (employee.getId().equals(id)) {
                Map<Long, EmployeeDTO> result = new HashMap<>();
                result.put(id, employee);
                return result;
            }
        }
        return null;
    }*/

    @GetMapping(path = "/id/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeeMap.values()
                .stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst().get();
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployeeMap() {
        return employeeMap.values().stream().collect(Collectors.toList());
    }

    @PostMapping
    public void addEmployee(@RequestBody EmployeeDTO employee) {
        employeeMap.put(employee.getId(), employee);
    }

    @PutMapping(path = "/id/{employeeId}")
    public void updateEmployee(@PathVariable(name = "employeeId") long id, @RequestBody EmployeeDTO employee) {
        if (employeeMap.containsKey(id)) {
            employeeMap.put(id, employee);
        }
    }


    @DeleteMapping(path = "/idByStream/{employeeId}")
    public void deleteEmployee(@PathVariable(name = "employeeId") Long id) {
        employeeMap.remove(id);
    }

    // 1.1
    @GetMapping(path = "/age/{age}")
    public List<EmployeeDTO> employeesOlderThan30(@PathVariable(name = "age") int age) {
        // create a list
        // find employee whose age is more than 30
        // return list
        List<EmployeeDTO> employees = new ArrayList<>();
        for (EmployeeDTO employee : employeeMap.values()) {
            if (employee.getAge() >= 30) {
                employees.add(employee);
            }
        }
        return employees;
    }

    @GetMapping(path = "/age1/{age}")
    public List<EmployeeDTO> employeesOlderThan30ByStream(@PathVariable(name = "age") int age) {
        return employeeMap.values()
                .stream()
                .filter(employee -> employee.getAge() >= 30)
                .collect(Collectors.toList());

    }

    @GetMapping(path = "/age/{age}")
    public List<EmployeeDTO> employeesAgeBetween25to40(@PathVariable(name = "age") int age) {
        List<EmployeeDTO> employees = new ArrayList<>();
        for (EmployeeDTO employee : employeeMap.values()) {
            if (employee.getAge() >= 25 && employee.getAge() <= 40) {
                employees.add(employee);
            }
        }
        return employees;
    }

    @GetMapping(path = "/ageByStream/{age}")
    public List<EmployeeDTO> employeesAgeBetween25to40ByStream() {
        return employeeMap.values()
                .stream()
                .filter(employee -> employee.getAge() >= 25 && employee.getAge() <= 40)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/cities/{cities}")
    public Set<EmployeeDTO> employeesUniqueCities(@PathVariable(name = "cities") String cities) {
        Set<EmployeeDTO> employees = new HashSet<>();
        for (EmployeeDTO employee : employeeMap.values()) {
            if (cities.contains(employee.getCity())) {
                employees.add(employee);
            }
        }
        return employees;
    }

    @GetMapping(path = "/citiesByStream/{cities}")
    public Set<EmployeeDTO> employeesUniqueCitiesByStream(@PathVariable(name = "cities") String cities) {
        Set<EmployeeDTO> employees = new HashSet<>();
        return employeeMap.values()
                .stream()
                .filter(employee -> cities.contains(employee.getCity()))
                .collect(Collectors.toSet());
    }

    @GetMapping(path = "/salaryAsc/{salary}")
    public List<EmployeeDTO> employeeSalaryByAscending(@PathVariable(name = "salary") String salary) {
        List<EmployeeDTO> employees = new ArrayList<>();
        return employeeMap.values()
                .stream()
                .sorted(Comparator.comparing(EmployeeDTO::getSalary))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/salaryDsc/{salary}")
    public List<EmployeeDTO> employeeSalaryByDescending(@PathVariable(name = "salary") String salary) {
        List<EmployeeDTO> employees = new ArrayList<>();
        return employeeMap.values()
                .stream()
                .sorted(Comparator.comparing(EmployeeDTO::getSalary).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/salary/lowest")
    public EmployeeDTO findEmployeeWithLowestSalary() {
        EmployeeDTO lowestSalaryEmployee = null;
        double lowestSalary = Double.MAX_VALUE;
        for (EmployeeDTO employee : employeeMap.values()) {
            if (employee.getSalary() < lowestSalary) {
                lowestSalary = employee.getSalary();
                lowestSalaryEmployee = employee;
            }
        }
        return lowestSalaryEmployee;
    }

    @GetMapping(path = "/salary/highest")
    public EmployeeDTO findEmployeeWithHighestSalary() {
        EmployeeDTO highestSalaryEmployee = null;
        double highestSalary = Double.MIN_VALUE;
        for (EmployeeDTO employee : employeeMap.values()) {
            if (employee.getSalary() > highestSalary) {
                highestSalary = employee.getSalary();
                highestSalaryEmployee = employee;
            }
        }
        return highestSalaryEmployee;
    }

    @GetMapping(path = "/salary/lowest")
    public EmployeeDTO findEmployeeWithLowestSalaryUsingStream() {
        return employeeMap.values()
                .stream()
                .min(Comparator.comparing(EmployeeDTO::getSalary))
                .orElse(null);
    }

    @GetMapping(path = "/salary/highest")
    public EmployeeDTO findEmployeeWithHighestSalaryUsingStream() {
        return employeeMap.values()
                .stream()
                .max(Comparator.comparing(EmployeeDTO::getSalary))
                .orElse(null);
    }

    @GetMapping(path = "/group/departments")
    public Map<String, Long> countEmployeesByDepartment() {
        return employeeMap.values()
                .stream()
                .collect(Collectors.groupingBy(
                        EmployeeDTO::getRole,
                        Collectors.counting()
                ));
    }

    public void dum() {
        EmployeeDTO employee = new EmployeeDTO(1l, "ravinder", "Singh",
                "rav123@gmail.com", 30, LocalDate.now(),
                "SDE", 65000d, "India");

        EmployeeDTO employee1 = new EmployeeDTO(2l, "pgb", "Sahib",
                "pgbsahib123@goto.com", 30, LocalDate.now(), "pgbHead",
                100000d, "Canada");

        employee1.compareTo(employee);
    }

    // API using Comparator

    // Sort by id
    @GetMapping(path = "/ascending/id/{id}")
    public List<EmployeeDTO> employeeIdComparator(@PathVariable(name = "id") Long id) {
        List<EmployeeDTO> employees = new ArrayList<>();
        return employeeMap.values()
                .stream()
                .sorted(new EmployeeIDComparator())
                .collect(Collectors.toList());
    }

    //Sort by firstName
    @GetMapping(path = "/ascending/firstName/{firstName}")
    public List<EmployeeDTO> employeeFirstNameComparator(@PathVariable(name = "firstName") String firstName) {
        List<EmployeeDTO> employees = new ArrayList<>();
        return employeeMap.values()
                .stream()
                .sorted(new EmployeeFirstNameComparator())
                .collect(Collectors.toList());
    }

    //Sort by lastName
    @GetMapping(path = "/Ascending/lastName/{lastName}")
    public List<EmployeeDTO> employeeLastNameComparator(@PathVariable(name = "lastName") String lastName) {
        List<EmployeeDTO> employees = new ArrayList<>();
        return employeeMap.values()
                .stream()
                .sorted(new EmployeeLastNameComparator())
                .collect(Collectors.toList());
    }

    //Sort by email
    @GetMapping(path = "/ascending/email/{Email}")
    public List<EmployeeDTO> employeeSalaryComparator(@PathVariable(name = "Email") Double salary) {
        List<EmployeeDTO> employees = new ArrayList<>();
        return employeeMap.values()
                .stream()
                .sorted(new EmployeeEmailComparator())
                .collect(Collectors.toList());
    }

    // Sort by age
    @GetMapping(path = "/Ascending/age/{Age}")
    public List<EmployeeDTO> employeeAgeComparator(@PathVariable(name = "Age") String salary) {
        List<EmployeeDTO> employees = new ArrayList<>();
        return employeeMap.values()
                .stream()
                .sorted(new EmployeeAgeComparator())
                .collect(Collectors.toList());
    }

    // Sort by DOB
    @GetMapping(path = "/Ascending/DOB/{DOB}")
    public List<EmployeeDTO> employeeDOBComparator(@PathVariable(name = "DOB") String salary) {
        List<EmployeeDTO> employees = new ArrayList<>();
        return employeeMap.values()
                .stream()
                .sorted(new EmployeeDOBComparator())
                .collect(Collectors.toList());
    }

    // Sort by role
    @GetMapping(path = "/Ascending/role/{Role}")
    public List<EmployeeDTO> employeeRoleComparator(@PathVariable(name = "Role") String salary) {
        List<EmployeeDTO> employees = new ArrayList<>();
        return employeeMap.values()
                .stream()
                .sorted(new EmployeeRoleComparator())
                .collect(Collectors.toList());
    }

    // Sort by salary
    @GetMapping(path = "/Ascending/city/{Salary}")
    public List<EmployeeDTO> employeeSalaryComparator(@PathVariable(name = "salary") String salary) {
        List<EmployeeDTO> employees = new ArrayList<>();
        return employeeMap.values()
                .stream()
                .sorted(new EmployeeSalaryComparator())
                .collect(Collectors.toList());
    }

    // Sort by city
    @GetMapping(path = "/Ascending/city/{City}")
    public List<EmployeeDTO> employeeCityComparator(@PathVariable(name = "salary") String salary) {
        List<EmployeeDTO> employees = new ArrayList<>();
        return employeeMap.values()
                .stream()
                .sorted(new EmployeeSalaryComparator())
                .collect(Collectors.toList());
    }

}













