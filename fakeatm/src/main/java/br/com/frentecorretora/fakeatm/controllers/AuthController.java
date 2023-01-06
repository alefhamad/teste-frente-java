package br.com.frentecorretora.fakeatm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

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
    public ResponseEntity<?> registerUser(@RequestBody SingUpDto singUpDto) {

        if (clienteRepo.existsByClienteCpf(singUpDto.getUsername())) {
            return new ResponseEntity<>("Nome de usuário já existe", HttpStatus.BAD_REQUEST);
        }

        ClienteModel cliente = new ClienteModel(singUpDto.getName(), singUpDto.getPassword(), singUpDto.getEndereco(),
                singUpDto.getUsername(), "ROLE_USER");

        clienteService.salvarClienteService(cliente);

        return new ResponseEntity<>("Usuário registrado com sucesso", HttpStatus.OK);
    }

}