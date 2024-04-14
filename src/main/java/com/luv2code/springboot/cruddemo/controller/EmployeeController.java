package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/list")
    public String listEmployees(Model model) {
        List<Employee> theEmployee = employeeService.findAll();

        model.addAttribute("employees", theEmployee);

        return "employees/list-employees";
    }

    @GetMapping("showAddForm")
    public String showAddForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);

        return "redirect:/employees/list";
    }


    @GetMapping("showUpdateForm")
    public String showUpdateForm(@RequestParam("employeeId") int theId, Model model) {
        Employee employee = employeeService.findById(theId);
        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }
}
