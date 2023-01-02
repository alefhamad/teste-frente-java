package br.com.frentecorretora.fakeatm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.frentecorretora.fakeatm.models.ClienteModel;
import br.com.frentecorretora.fakeatm.repos.ClienteRepo;

@Service
public class SenhaService {

    @Autowired
    private ClienteRepo clienteRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String senhaEncoder(String senha){
        return encoder.encode(senha);
    }

    //create a class to validate the password
    public boolean validaSenha(ClienteModel cliente){
        return encoder.matches(cliente.getClienteSenha(), clienteRepo.findByClienteCpf(cliente.getClienteCpf()).getClienteSenha());
    }


}