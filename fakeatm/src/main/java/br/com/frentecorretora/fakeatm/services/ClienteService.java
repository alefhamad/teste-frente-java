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

    //auto wire the SenhaService class
    @Autowired
    private SenhaService senhaService;



    public ClienteModel salvarClienteService(ClienteModel cliente){
        cliente.setClienteSenha(senhaService.senhaEncoder(cliente.getClienteSenha()));
        clienteRepo.save(cliente);
        ContaModel conta = new ContaModel(contaService.geraRandomUnico(), cliente);
        conta = contaRepo.save(conta);
        return cliente;
    }

    public Long deleteClienteId(Long cliente){

        clienteRepo.deleteById(cliente);
        return cliente;
    }

}
