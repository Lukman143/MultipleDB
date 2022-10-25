package com.employee.Employee.controller;

import com.employee.Employee.primaryDB.Employee;
import com.employee.Employee.primaryDB.EmployeeRepo;
import com.employee.Employee.secondaryDB.Manager;
import com.employee.Employee.secondaryDB.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@EnableTransactionManagement
public class MultiDBController {

    @Autowired
    private EmployeeRepo employeeRepo ;

    @Autowired
    private ManagerRepo managerRepo ;

    @GetMapping("/getEmployees")
    public List<Employee> getAllEmployees(){
       return employeeRepo.findAll();
    }

    @GetMapping("/getManagers")
    public List<Manager> getAllManagers(){
        return managerRepo.findAll();
    }
}
