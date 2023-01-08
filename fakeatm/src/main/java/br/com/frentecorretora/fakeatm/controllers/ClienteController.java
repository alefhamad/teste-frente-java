package br.com.frentecorretora.fakeatm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frentecorretora.fakeatm.dto.UsuarioDto;
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

    @GetMapping("/eu")

    public ClienteModel listaClientes() {
        return clienteService.retornaClienteContexto();
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> delete(@RequestBody UsuarioDto usuario) {
        if (Boolean.FALSE.equals(auth.isCliente(usuario.getUsername()))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Você não tem permissão para acessar este recurso");
        }
        clienteRepo.deleteClienteByClienteCpf(usuario.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> update(@RequestBody UsuarioDto usuario) {
        if (Boolean.FALSE.equals(auth.isCliente(usuario.getUsername()))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Você não tem permissão para acessar este recurso");
        }
        clienteService.atualizaCliente(usuario);
        return ResponseEntity.ok().body("Usuário " + usuario.getUsername() + " atualizado com sucesso");
    }

}
