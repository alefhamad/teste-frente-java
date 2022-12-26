package br.com.frentecorretora.fakeatm.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.frentecorretora.fakeatm.models.ClienteModel;
import br.com.frentecorretora.fakeatm.repos.ClienteRepo;

@Controller
public class ClienteNotRestController {
    
    @Autowired
    private ClienteRepo clienteRepo;
  
    @GetMapping("/clientes")
        public ArrayList<ClienteModel> listaClientes(Model model) {
            ArrayList<ClienteModel> listaClientes = (ArrayList<ClienteModel>) clienteRepo.findAll();
            model.addAttribute("clientes", listaClientes);
            return listaClientes;
    }
}
