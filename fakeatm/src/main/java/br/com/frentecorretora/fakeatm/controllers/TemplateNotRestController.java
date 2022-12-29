package br.com.frentecorretora.fakeatm.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.frentecorretora.fakeatm.models.ClienteModel;
import br.com.frentecorretora.fakeatm.models.PacoteModel;
import br.com.frentecorretora.fakeatm.repos.ClienteRepo;
import br.com.frentecorretora.fakeatm.services.PacoteService;

@Controller
@CrossOrigin("*")
@RequestMapping("/private/")
public class TemplateNotRestController {
    
    @Autowired 
    private PacoteService pacoteService;

    @Autowired 
    private ClienteRepo clienteRepo;


    //create a get mapping that receives a ClienteModel And returns an ArrayList o PacoteModel
    @GetMapping("/pacotes/clientepage/{id}")
    String listaPacotesPorUsuario(@PathVariable("id") Long id, Model model) {
        ArrayList<PacoteModel> listaPacotes = (ArrayList<PacoteModel>) pacoteService.listaPacotes(id);
        model.addAttribute("pacotes", listaPacotes);
        return  "clientepage";
    }

    @GetMapping("/clientes")
            String listaClientes(Model model) {
            ArrayList<ClienteModel> listaClientes = (ArrayList<ClienteModel>) clienteRepo.findAll();
            model.addAttribute("clientes", listaClientes);
            return "clientes";
    }

    @GetMapping("/clientes/encontrado/{id}")
    String listaCliente(@PathVariable("id") Long id, Model model) {
        ClienteModel cliente = clienteRepo.findById(id).get();
        model.addAttribute("cliente", cliente);
        return "encontrato";
    }
    
}
