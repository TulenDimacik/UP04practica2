package com.example.practica2.controllers;


import com.example.practica2.models.ClientInformation;
import com.example.practica2.models.EmployeeInformation;
import com.example.practica2.models.Passport;
import com.example.practica2.repo.ClientRepository;
import com.example.practica2.repo.EmployeeRepository;
import com.example.practica2.repo.PassporRepository;
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
    @Autowired
    private PassporRepository passporRepository;

    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("/employee")
    public String employeeMain(Model model)
    {
        Iterable<Passport> passports = passporRepository.findAll();
        model.addAttribute("passports", passports);
        Iterable<EmployeeInformation> employeeinformations = employeeRepository.findAll();// модель из которой получаем все значения
        model.addAttribute("employeeinformations", employeeinformations);//добавляем атрибут и передаем все получаемые значения
        return "mainPage2";
    }

    @GetMapping("/employee/add") //переходит на вью
    public String employeeAdd(@ModelAttribute("employee")EmployeeInformation employeeInformation, Model model)
    {
        Iterable<Passport> passports = passporRepository.findAll();
        model.addAttribute("passports", passports);
        return "addEmployee";
    }

    @PostMapping("/employee/add")// добавление в бд
    public String employeeAdd(@ModelAttribute("employee") @Valid EmployeeInformation employeeInformation,
                              @RequestParam String number,
                              BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()) {
            Iterable<Passport> passports = passporRepository.findAll();
            model.addAttribute("passports", passports);
            return "addEmployee";
        }
        employeeInformation.setPassport(passporRepository.findByNumber(number));
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


//    @GetMapping("/employee/client")
//    private String Main(Model model){
//        Iterable<ClientInformation> clients = clientRepository.findAll();
//        model.addAttribute("clients", clients);
//        Iterable<EmployeeInformation> employes = employeeRepository.findAll();
//        model.addAttribute("employes", employes);
//        return "manyToMany";
//    }
//    @PostMapping("/employee/client/add")
//    public String employeeClientAdd(@RequestParam Long employee, @RequestParam Long client, Model model)
//    {
//        ClientInformation client2 = clientRepository.findById(client).orElseThrow();
//        EmployeeInformation employee2 = employeeRepository.findById(employee).orElseThrow();
//        employee2.getClients().add(client2);
//        employeeRepository.save(employee2);
//        return "redirect:/employee";
//    }
//
//    @PostMapping("employee/delClient/{id_employee}/{id_client}")
//    public String blogPostDell(@PathVariable(value = "id_employee") Long id_employee, @PathVariable(value = "id_client") Long id_client, Model model)
//    {
//        ClientInformation client2 = clientRepository.findById(id_client).orElseThrow();
//        EmployeeInformation employee2 = employeeRepository.findById(id_employee).orElseThrow();
//        employee2.getClients().remove(employee2);
//        employeeRepository.save(employee2);
//        return "redirect:/employee";
//    }

    @GetMapping("/passport")
    public String Main(Model model){
        Iterable<Passport> passports = passporRepository.findAll();
        model.addAttribute("passports", passports);
        return "addPassport";
    }

    @PostMapping("/passport/add")
    public String passportAdd(@ModelAttribute("passport") Passport passport)
    {
        passporRepository.save(passport);
        return "redirect:/employee";
    }
}
