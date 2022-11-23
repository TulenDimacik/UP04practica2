package com.example.practica2.controllers;


import com.example.practica2.models.ClientInformation;
import com.example.practica2.models.EmployeeInformation;
import com.example.practica2.repo.ClientRepository;
import com.example.practica2.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String clientAdd(Model model)
    {
        return "addClient";
    }

    @PostMapping("/client/add")// добавление в бд
    public String clientAdd(@RequestParam String clientName,
                              @RequestParam (defaultValue = "0") double orderCost,
                              @RequestParam (defaultValue = "0") int clientAge,
                              @RequestParam (defaultValue = "0") float orderWeight,
                              @RequestParam (defaultValue = "false")boolean payment, Model model)
    {
        ClientInformation information = new ClientInformation(clientName, orderCost, clientAge, orderWeight, payment);
        clientRepository.save(information);
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
        model.addAttribute("info",res);
        if(!clientRepository.existsById(id)){
            return "redirect:/";
        }
        return "client-details";
    }
    @GetMapping ("/client/{id}/edit")
    public  String clientEdit(@PathVariable ("id") long id, Model model)
    {
        if(!clientRepository.existsById(id)){
        return "redirect:/";
    }
        Optional<ClientInformation> information = clientRepository.findById(id);
        ArrayList<ClientInformation> res = new ArrayList<>();
        information.ifPresent(res::add);
        model.addAttribute("info",res);

        return "client-edit";
    }
    @PostMapping ("/client/{id}/edit")
    public  String clientUpdate(@PathVariable("id")long id,
                                @RequestParam String clientName,
                                @RequestParam (defaultValue = "0") double orderCost,
                                @RequestParam (defaultValue = "0") int clientAge,
                                @RequestParam (defaultValue = "0") float orderWeight,
                                @RequestParam (defaultValue = "false")boolean payment, Model model)
    {
        ClientInformation clientInformation = clientRepository.findById(id).orElseThrow();
        clientInformation.setClientName(clientName);
        clientInformation.setClientAge(clientAge);
        clientInformation.setOrderCost(orderCost);
        clientInformation.setOrderWeight(orderWeight);
        clientInformation.setPayment(payment);
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
