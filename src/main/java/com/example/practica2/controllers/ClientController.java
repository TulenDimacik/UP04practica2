package com.example.practica2.controllers;


import com.example.practica2.models.ClientInformation;
import com.example.practica2.models.EmployeeInformation;
import com.example.practica2.repo.ClientRepository;
import com.example.practica2.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("/")
    public String clientMain(Model model)
    {
        Iterable<ClientInformation> clientInformations = clientRepository.findAll();// модель из которой получаем все значения
        model.addAttribute("clientInformations", clientInformations);//добавляем атрибут и передаем все получаемые значения
        return "mainPage";
    }
    @GetMapping("/client/add") //переходит на вью
    public String clientAdd(@ModelAttribute("clients") ClientInformation clientInformation)
    {
        return "addClient";
    }

    @PostMapping("/client/add")// добавление в бд
    public String clientAdd(@ModelAttribute("clients") @Valid ClientInformation clientInformation, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "addClient";
        clientRepository.save(clientInformation);
        return "redirect:/";
    }
    @GetMapping("/client/filter")
    public String clientFilter(Model model)
    {
        return "clientFilter";
    }
    @PostMapping("/client/filter/result")
    public String clientResult(@RequestParam String clientName, Model model)
    {
        List<ClientInformation> result = clientRepository.findByClientNameContains(clientName);
        List<ClientInformation> result1 = clientRepository.findByClientNameEquals(clientName);
        model.addAttribute("result", result);
        model.addAttribute("result1",result1);
        return "clientFilter";
    }

    @GetMapping ("/client/{id}")
    public  String clientDetails(@PathVariable(value = "id")long id, Model model)
    {
        Optional<ClientInformation> info = clientRepository.findById(id);
        ArrayList<ClientInformation> res = new ArrayList<>();
        info.ifPresent(res::add);
        model.addAttribute("abjobja",res);
        if(!clientRepository.existsById(id)){
            return "redirect:/";
        }
        return "client-details";
    }
    @GetMapping ("/client/{id}/edit")
    public  String clientEdit(@PathVariable ("id") long id, Model model)
    {
        ClientInformation res = clientRepository.findById(id).orElseThrow();
        model.addAttribute("abjobja",res);
        return "client-edit";
    }

    @PostMapping ("/client/{id}/edit")
    public  String clientUpdate(@ModelAttribute("abjobja") @Valid ClientInformation clientInformation, BindingResult bindingResult,
                                @PathVariable("id")long id)
    {
        if(bindingResult.hasErrors())
            return "client-edit";
        clientRepository.save(clientInformation);
        return "redirect:/";
    }
   @PostMapping("/client/{id}/remove")
    public String clientDelete(@PathVariable("id") long id, Model model){
        ClientInformation clientInformation = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(clientInformation);
        return "redirect:/";
    }

}
