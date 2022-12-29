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

    

    
    public PacoteModel salvarPacoteService(PacoteModel pacote){
        

        Instant dataAgora = Instant.now();
        PacoteModel novoPacote = new PacoteModel(dataAgora, dataAgora, dataAgora, pacote.getConta());
        pacoteRepo.save(novoPacote);
        System.out.println(novoPacote);
        pacote = novoPacote;
        // PacoteRepo pacoteSalvo = pacoteRepo.findById(pacote.getId());
        return pacote;
    }

    //Create a class that receives a clienteModel then gets the accoutn of that client then finds every pacote of that conta
    public ArrayList<PacoteModel> listaPacotes(Long id){
        //find the cliente by id
        ClienteModel cliente = new ClienteModel();
        cliente = clienteRepo.findById(id).get();

        //print cliente to the console and concatenate a frase
        System.out.println("Cliente;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; " + cliente);


        ContaModel conta = cliente.getConta();
        //print conta to the cosole
        System.out.println(conta);

        ArrayList<PacoteModel> listaPacotes = (ArrayList<PacoteModel>) pacoteRepo.findAllByConta(conta);
        return listaPacotes;
    }
    
    
}
