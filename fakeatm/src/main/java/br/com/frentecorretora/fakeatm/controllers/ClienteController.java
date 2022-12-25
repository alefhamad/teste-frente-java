package br.com.frentecorretora.fakeatm.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frentecorretora.fakeatm.models.ClienteModel;
import br.com.frentecorretora.fakeatm.repos.ClienteRepo;
import br.com.frentecorretora.fakeatm.services.ClienteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    public ClienteService clienteService;

    @GetMapping("/listar")
    public ArrayList<ClienteModel> listaClientes() {
        ArrayList<ClienteModel> listaClientes = (ArrayList<ClienteModel>) clienteRepo.findAll();
        return listaClientes;
    }

    @PostMapping("/criar")
    //post mapping to create new client ans save it to the database
    public ResponseEntity<ClienteModel> criarCliente(@RequestBody ClienteModel cliente) {
        ClienteModel clienteSalvo = clienteService.salvarClienteService(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/deletar/{idCliente}")
    public ResponseEntity <Long> deletaCliente(@PathVariable long idCliente){
        
        clienteService.deleteClienteId(idCliente);
        return ResponseEntity.noContent().build();
    }

    
}
