package br.com.frentecorretora.fakeatm.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
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
import br.com.frentecorretora.fakeatm.services.AuthService;
import br.com.frentecorretora.fakeatm.services.ClienteService;
import br.com.frentecorretora.fakeatm.services.SenhaService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/cliente")
public class ClienteController {
    

    @Autowired
    public ClienteService clienteService;

    @Autowired
    public SenhaService senha;

    @Autowired
    public ClienteRepo clienteRepo;

    @Autowired
    public AuthService auth;

    @GetMapping("/listar")

    public List<ClienteModel> listaClientes(){
        return clienteService.listaClientesService();
    }
        
    @GetMapping("/senha/")
    public ResponseEntity<String> alteraSenha(@RequestBody ClienteModel cliente){
        if(senha.validaSenha(cliente)){
        return ResponseEntity.ok().body("Senha certa!");
        }else{
        return ResponseEntity.badRequest().body("Senha errada!") ;
        }
    }

    @PostMapping("/criar")
    //post mapping to create new client ans save it to the database
    public ResponseEntity<String> criarCliente(@RequestBody ClienteModel cliente){
        ClienteModel clienteSalvo = clienteService.salvarClienteService(cliente);
        if(clienteSalvo != null){
        return ResponseEntity.ok().body("Cliente salvo com sucesso!");
        }else{
        return ResponseEntity.badRequest().body(cliente.getClienteCpf() + " já cadastrado") ;
        }
    }

    @DeleteMapping("/deletar/{cpf}")
    public ResponseEntity <Long> deletaCliente(@PathVariable String cpf){
        
        clienteRepo.deleteClienteByClienteCpf(cpf);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/valida/{cpf}")
    public ResponseEntity<Boolean> validaCliente(@PathVariable("cpf") String cpf){
        return ResponseEntity.ok(clienteService.validaCpf(cpf));
    }

    @PostMapping("/valida")
    //receive a clientemodel and returns a string
    public ResponseEntity<String> validaCliente(@RequestBody ClienteModel cliente){
        return ResponseEntity.ok(clienteService.validaClienteModel(cliente));
    }

    @PutMapping("/atualizar/{cpf}")
    public ResponseEntity<String> deletaCliente(@PathVariable String cpf, @RequestBody ClienteModel cliente){

        return ResponseEntity.ok().body(clienteRepo.save(cliente).getClienteCpf());
    }

    @GetMapping("/ver/{cpf}")
    public ResponseEntity<Optional<ClienteModel>> verCliente(@PathVariable("cpf") String cpf){
        try {
            if(!auth.isCliente(cpf)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.ok(clienteRepo.findByClienteCpf(cpf));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
