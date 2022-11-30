package com.example.practica2.controllers;


import com.example.practica2.models.Adress;
import com.example.practica2.models.ClientInformation;
import com.example.practica2.models.EmployeeInformation;
import com.example.practica2.models.Product;
import com.example.practica2.repo.AddressRepository;
import com.example.practica2.repo.ClientRepository;
import com.example.practica2.repo.EmployeeRepository;
import com.example.practica2.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AddressRepository addressRepository;
    @GetMapping("/")
    public String clientMain(Model model)
    {
        Iterable<Adress> address = addressRepository.findAll();
        model.addAttribute("address",address);
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        Iterable<ClientInformation> clientInformations = clientRepository.findAll();// модель из которой получаем все значения
        model.addAttribute("clientInformations", clientInformations);//добавляем атрибут и передаем все получаемые значения
        return "mainPage";
    }
    @GetMapping("/client/add") //переходит на вью
    public String clientAdd(@ModelAttribute("clients") ClientInformation clientInformation, Model model)
    {
        Iterable<Adress> address = addressRepository.findAll();
        model.addAttribute("address",address);

        return "addClient";
    }

    @PostMapping("/client/add")// добавление в бд
    public String clientAdd(@ModelAttribute("clients") @Valid ClientInformation clientInformation,
                            BindingResult bindingResult,
                            @RequestParam String street,
                            Model model)
    {
        if(bindingResult.hasErrors()) {
            Iterable<Adress> address = addressRepository.findAll();
            model.addAttribute("address",address);
            return "addClient";
        }
        clientInformation.setAddress(addressRepository.findByStreet(street));
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

    @GetMapping("/product")
    public String product(Model model){
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        return "addProduct";
    }

    @PostMapping("/product/add")
    public String productAdd(@ModelAttribute("product") Product product)
    {
        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping("/client/{id_client}/{id_product}/remove")
    public  String clientProductDelete(@PathVariable(value = "id_client") long id_client,
                                       @PathVariable(value = "id_product") Long id_product,
                                       Model model)
    {
        ClientInformation client = clientRepository.findById(id_client).orElseThrow();
        Product product = productRepository.findById(id_product).orElseThrow();
        client.getProducts().remove(product);
        clientRepository.save(client);
        return "redirect:/";
    }

    @GetMapping("/client/product/add")
    private String Main(Model model){
        Iterable<ClientInformation> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "manyToMany";
    }

    @PostMapping("/client/product/add")
    public String blogPostAdd(@RequestParam Long clients, @RequestParam Long products, Model model)
    {
        ClientInformation clientInformation = clientRepository.findById(clients).orElseThrow();
        Product product1 = productRepository.findById(products).orElseThrow();
        clientInformation.getProducts().add(product1);
        clientRepository.save(clientInformation);
        return "redirect:/";
    }

}
