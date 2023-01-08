package br.com.frentecorretora.fakeatm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frentecorretora.fakeatm.dto.LoginDto;
import br.com.frentecorretora.fakeatm.dto.SingUpDto;
import br.com.frentecorretora.fakeatm.models.ClienteModel;
import br.com.frentecorretora.fakeatm.repos.ClienteRepo;
import br.com.frentecorretora.fakeatm.services.ClienteService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getCpfDto(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("Login realizado com sucesso!. ", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Usuário ou senha incorretos: ", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SingUpDto singUpDto) {

        if (Boolean.TRUE.equals(clienteRepo.existsByClienteCpf(singUpDto.getUsername()))) {
            return new ResponseEntity<>("Nome de usuário já existe", HttpStatus.BAD_REQUEST);
        }
        ClienteModel cliente = new ClienteModel(singUpDto.getName(), singUpDto.getPassword(), singUpDto.getEndereco(),singUpDto.getUsername(), "ROLE_USER", singUpDto.getDataDeNascimento());
        
        //try to sabe the user to the database using clienteService if it returns null catch the exception and return a bad request
        try {
            ClienteModel clienteSave = clienteService.salvarClienteService(cliente);
            if(clienteSave == null){
                return new ResponseEntity<>("Erro ao registrar o usuário", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Usuário registrado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao registrar o usuário", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<String> logoutUser() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return new ResponseEntity<>("Logout realizado com sucesso", HttpStatus.OK);
    }
}

