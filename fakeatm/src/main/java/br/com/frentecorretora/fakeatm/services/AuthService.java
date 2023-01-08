package br.com.frentecorretora.fakeatm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.frentecorretora.fakeatm.repos.ClienteRepo;
import br.com.frentecorretora.fakeatm.repos.ContaRepo;

@Service
public class AuthService {

    @Autowired
    private ContaRepo contaRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    public static String returnPrincipal(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().split(",")[0].split("=")[1];
    }

    public Boolean isCliente(String cpf){
        return returnPrincipal().equals(cpf);
    }

    public Boolean isConta(String conta){
        String cpf = clienteRepo.findByContaIdConta(contaRepo.findByContaNumero(conta).getIdConta()).getClienteCpf();
        return cpf.equals(returnPrincipal());
    }

}