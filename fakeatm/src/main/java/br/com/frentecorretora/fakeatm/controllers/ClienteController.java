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
import br.com.frentecorretora.fakeatm.services.ClienteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cliente")
public class ClienteController {
    

    @Autowired
    public ClienteService clienteService;

    @GetMapping("/listar")

    public ArrayList<ClienteModel> listaClientes(){
        return clienteService.listaClientesService();
    }
        

    @PostMapping("/criar")
    //post mapping to create new client ans save it to the database
    public ResponseEntity<String> criarCliente(@RequestBody ClienteModel cliente){
        ClienteModel clienteSalvo = clienteService.salvarClienteService(cliente);
        //return ResponseEntity.ok(clienteSalvo);
        //return the client saved if null return bad request
        if(clienteSalvo != null){
        return ResponseEntity.ok().body("Cliente salvo com sucesso!");
        }else{
        return ResponseEntity.badRequest().body(cliente.getClienteCpf().toString() + " já cadastrado") ;
        }
    }

    @DeleteMapping("/deletar/{idCliente}")
    public ResponseEntity <Long> deletaCliente(@PathVariable long idCliente){
        
        clienteService.deleteClienteId(idCliente);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/valida/{cpf}")
    public ResponseEntity<Boolean> validaCliente(@PathVariable("cpf") String cpf){
        boolean valida = clienteService.validaCpf(cpf);
        return ResponseEntity.ok(valida);
    }

    @PostMapping("/valida")
    //receive a clientemodel and returns a string
    public ResponseEntity<String> validaCliente(@RequestBody ClienteModel cliente){
        String valida = clienteService.validaClienteModel(cliente);
        return ResponseEntity.ok(valida);
    }
}
