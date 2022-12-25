package br.com.frentecorretora.fakeatm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frentecorretora.fakeatm.models.ClienteModel;
import br.com.frentecorretora.fakeatm.models.ContaModel;
import br.com.frentecorretora.fakeatm.repos.ClienteRepo;
import br.com.frentecorretora.fakeatm.repos.ContaRepo;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired 
    private ContaRepo contaRepo;

    @Autowired
    private GeraContaService contaService;

    public ClienteModel salvarClienteService(ClienteModel cliente){


        //while random number is not unique in the database, keep creating a new number

        clienteRepo.save(cliente);
        String random = contaService.geraRandomUnico();
        //
        ContaModel conta = new ContaModel(random, cliente);
        conta = contaRepo.save(conta);
        cliente.setConta(conta);
        return cliente;
    }

}
