package br.com.frentecorretora.fakeatm.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frentecorretora.fakeatm.models.ContaModel;
import br.com.frentecorretora.fakeatm.repos.ContaRepo;

@Service
public class GeraContaService {

    @Autowired 
    private ContaRepo contaRepo;

    public String geraRandom(){
        
        Random rand = new Random();
        int numeroConta = rand.nextInt(900000) + 100000;

        return String.valueOf(numeroConta);
    }

    public String geraRandomUnico(){
        ContaModel numeroConta;
        String numeroRandomico;
        do {
            numeroRandomico = geraRandom();
            numeroConta = contaRepo.findByContaNumero(numeroRandomico);
        } while (numeroConta != null);
            return numeroRandomico;
    }
}
