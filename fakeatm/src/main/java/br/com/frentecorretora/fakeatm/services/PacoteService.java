package br.com.frentecorretora.fakeatm.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frentecorretora.fakeatm.models.ContaModel;
import br.com.frentecorretora.fakeatm.models.PacoteModel;
import br.com.frentecorretora.fakeatm.repos.ContaRepo;
import br.com.frentecorretora.fakeatm.repos.PacoteRepo;

@Service
public class PacoteService {


    @Autowired
    private PacoteRepo pacoteRepo;

    @Autowired
    private ContaRepo contaRepo;

    public PacoteModel salvarPacoteService(PacoteModel pacote){
        

        Instant dataAgora = Instant.now();
        PacoteModel novoPacote = new PacoteModel(dataAgora, dataAgora, dataAgora, pacote.getConta());
        pacoteRepo.save(novoPacote);
        System.out.println(novoPacote);
        pacote = novoPacote;
        // PacoteRepo pacoteSalvo = pacoteRepo.findById(pacote.getId());
        return pacote;
    }
}
