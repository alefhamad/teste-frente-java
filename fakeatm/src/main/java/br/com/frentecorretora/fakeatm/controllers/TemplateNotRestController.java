package br.com.frentecorretora.fakeatm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.frentecorretora.fakeatm.repos.ClienteRepo;
import br.com.frentecorretora.fakeatm.services.ClienteService;

@Controller
@CrossOrigin("*")
@RequestMapping("/private/")
public class TemplateNotRestController {

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private ClienteService clienteService;

    // create a get mapping that receives a ClienteModel And returns an ArrayList o
    // PacoteModel
    @GetMapping("/clientes")
    String listaClientes(Model model) {
        model.addAttribute("clientes", clienteService.listaClientesService());
        return "clientes";
    }

    @GetMapping("/clientes/encontrado/{id}")
    String listaCliente(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cliente", clienteRepo.findById(id).isPresent());
        return "encontrato";
    }

    @GetMapping("/clientes/conta/{id}")
    String listaConta(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cliente", clienteRepo.findById(id).isPresent());
        return "conta";
    }

}
