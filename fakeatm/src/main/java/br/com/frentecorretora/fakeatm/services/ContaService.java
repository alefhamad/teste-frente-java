package br.com.frentecorretora.fakeatm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frentecorretora.fakeatm.models.ContaModel;
import br.com.frentecorretora.fakeatm.repos.ContaRepo;

@Service
public class ContaService {
    
    @Autowired
    private ContaRepo contaRepo;

    //find all pacotes by conta
    public ContaModel findByContaNumero(String contaNumero){
        return contaRepo.findByContaNumero(contaNumero);
    }
    


}
