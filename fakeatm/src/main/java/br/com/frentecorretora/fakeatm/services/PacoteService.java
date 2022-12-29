package br.com.frentecorretora.fakeatm.services;

import java.time.Instant;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frentecorretora.fakeatm.models.ClienteModel;
import br.com.frentecorretora.fakeatm.models.ContaModel;
import br.com.frentecorretora.fakeatm.models.PacoteModel;
import br.com.frentecorretora.fakeatm.repos.ClienteRepo;
import br.com.frentecorretora.fakeatm.repos.PacoteRepo;

@Service
public class PacoteService {


    @Autowired
    private PacoteRepo pacoteRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    

    
    public PacoteModel criarPacoteService(PacoteModel pacote){
        
        PacoteModel novoPacote = new PacoteModel(pacote.getConta());
        pacoteRepo.save(novoPacote);
        return novoPacote;
    }

    public ArrayList<PacoteModel> listaPacotes(Long id){
        ClienteModel cliente = new ClienteModel();
        cliente = clienteRepo.findById(id).get();
        ContaModel conta = cliente.getConta();
        System.out.println(conta);
        ArrayList<PacoteModel> listaPacotes = (ArrayList<PacoteModel>) pacoteRepo.findAllByConta(conta);
        return listaPacotes;
    }
    
    
}
