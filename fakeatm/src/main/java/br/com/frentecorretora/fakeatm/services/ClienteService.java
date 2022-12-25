package br.com.frentecorretora.fakeatm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


        String encodedSenha = senhaService.senhaEncoder(cliente.getClienteSenha());
        cliente.setClienteSenha(encodedSenha);
        clienteRepo.save(cliente);
        String random = contaService.geraRandomUnico();
        ContaModel conta = new ContaModel(random, cliente);
        conta = contaRepo.save(conta);
        cliente.setClienteSenha("");
        cliente.setConta(conta);
        return cliente;
    }

    public Long deleteClienteId(Long cliente){

        clienteRepo.deleteById(cliente);
        return cliente;
    }

}
