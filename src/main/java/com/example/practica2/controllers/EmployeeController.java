package com.example.practica2.controllers;


import com.example.practica2.models.EmployeeInformation;
import com.example.practica2.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("/empl")
    public String employeeMain(Model model)
    {
        Iterable<EmployeeInformation> employeeinformations = employeeRepository.findAll();// модель из которой получаем все значения
        model.addAttribute("employeeinformations", employeeinformations);//добавляем атрибут и передаем все получаемые значения
        return "mainPage2";
    }

    @GetMapping("/employee/add") //переходит на вью
    public String employeeAdd(Model model)
    {
        return "addEmployee";
    }

    @PostMapping("/employee/add")// добавление в бд
    public String employeeAdd(@RequestParam String employeeName,
                              @RequestParam (defaultValue = "0") double salary,
                              @RequestParam (defaultValue = "0") int age,
                              @RequestParam (defaultValue = "0") float height,
                              @RequestParam (defaultValue = "false") boolean deletedEmployee, Model model)
    {
        EmployeeInformation information = new EmployeeInformation(employeeName, salary, age, height, deletedEmployee);
        employeeRepository.save(information);
        return "redirect:/";
    }

    @GetMapping("/employee/filter")
    public String employeeFilter(Model model)
    {
        return "employeeFilter";
    }

    @PostMapping("/employee/filter/result")
    public String employeeResult(@RequestParam String employeeName, Model model)
    {
        List<EmployeeInformation> result = employeeRepository.findByEmployeeNameContains(employeeName);
        List<EmployeeInformation> result1 = employeeRepository.findByEmployeeNameEquals(employeeName);
        model.addAttribute("result", result);
        model.addAttribute("result1",result1);
        return "employeeFilter";
    }

}
