package br.com.frentecorretora.fakeatm.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public Boolean isCliente(String cpf){
        String clienteCpf = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().split(",")[0].split("=")[1];
        if(clienteCpf.equals(cpf)){
            return true;
        }else{
            return false;
        }
    }
}
