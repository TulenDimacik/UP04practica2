package com.example.practica2.controllers;


import com.example.practica2.models.ClientInformation;
import com.example.practica2.models.EmployeeInformation;
import com.example.practica2.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("/employee")
    public String employeeMain(Model model)
    {
        Iterable<EmployeeInformation> employeeinformations = employeeRepository.findAll();// модель из которой получаем все значения
        model.addAttribute("employeeinformations", employeeinformations);//добавляем атрибут и передаем все получаемые значения
        return "mainPage2";
    }

    @GetMapping("/employee/add") //переходит на вью
    public String employeeAdd(@ModelAttribute("employee") EmployeeInformation employeeInformation)
    {
        return "addEmployee";
    }

    @PostMapping("/employee/add")// добавление в бд
    public String employeeAdd(@ModelAttribute("employee") @Valid EmployeeInformation employeeInformation, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "addEmployee";
        employeeRepository.save(employeeInformation);
        return "redirect:/employee";
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

    @GetMapping ("/employee/{id}")
    public  String employeeDetails(@PathVariable(value = "id")long id, Model model)
    {
        Optional<EmployeeInformation> info = employeeRepository.findById(id);
        ArrayList<EmployeeInformation> res = new ArrayList<>();
        info.ifPresent(res::add);
        model.addAttribute("info1",res);
        if(!employeeRepository.existsById(id)){
            return "redirect:/employee";
        }
        return "employee-details";
    }
    @GetMapping ("/employee/{id}/edit")
    public  String employeeEdit(@PathVariable ("id") long id, Model model)
    {
//        if(!employeeRepository.existsById(id)){
//            return "redirect:/employee";
//        }
//        Optional<EmployeeInformation> information = employeeRepository.findById(id);
//        ArrayList<EmployeeInformation> res = new ArrayList<>();
//        information.ifPresent(res::add);
        EmployeeInformation res = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("aboba",res);
        return "employee-edit";
    }
    @PostMapping ("/employee/{id}/edit")
    public  String employeeUpdate(@ModelAttribute("aboba") @Valid EmployeeInformation employeeInformation, BindingResult bindingResult,
                                  @PathVariable("id")long id)
    {
        if(bindingResult.hasErrors())
            return "employee-edit";
        employeeRepository.save(employeeInformation);
        return "redirect:/employee";

    }
    @PostMapping("/employee/{id}/remove")
    public String employeeDelete(@PathVariable("id") long id, Model model){
        EmployeeInformation employeeInformation = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employeeInformation);
        return "redirect:/employee";
    }


}
