package br.com.frentecorretora.fakeatm.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frentecorretora.fakeatm.models.ClienteModel;
import br.com.frentecorretora.fakeatm.repos.ClienteRepo;

@RestController
@CrossOrigin("*") 

@RequestMapping("/api")
public class ClienteController {
    
    @Autowired
    private ClienteRepo clienteRepo;

    //create a post mapping to receive the new client and save it to the databas
    @PostMapping("/clientes")
    public ResponseEntity<ClienteModel> createCliente(@RequestBody ClienteModel cliente){
        ClienteModel clienteModel = clienteRepo.save(cliente);
        return ResponseEntity.ok(clienteModel);
    }
    
    @GetMapping("/clientes")
    public ArrayList<ClienteModel> listaClientes(){
        ArrayList<ClienteModel> listaClientes = (ArrayList<ClienteModel>) clienteRepo.findAll();

        return listaClientes;
    }
}
