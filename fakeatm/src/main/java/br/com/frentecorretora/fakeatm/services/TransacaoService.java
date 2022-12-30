package br.com.frentecorretora.fakeatm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frentecorretora.fakeatm.models.ContaModel;
import br.com.frentecorretora.fakeatm.models.PacoteModel;
import br.com.frentecorretora.fakeatm.models.TransacaoModel;
import br.com.frentecorretora.fakeatm.repos.PacoteRepo;
import br.com.frentecorretora.fakeatm.repos.TransacaoRepo;

@Service
public class TransacaoService {

    @Autowired 
    private TransacaoRepo transacaoRepo;

    @Autowired
    private PacoteService pacoteService;

    @Autowired
    private PacoteRepo pacoteRepo;

    

    //public TransacaoModel listarTransacoes()
    public TransacaoModel criarTransacaoVaziaService(ContaModel conta) {
        PacoteModel newPacote = new PacoteModel(conta);
        pacoteRepo.save(newPacote);
        TransacaoModel newTransacao = new TransacaoModel(newPacote);
        transacaoRepo.save(newTransacao);
        
        return newTransacao;
    }

    public TransacaoModel criarTransacaoCompletaService(TransacaoModel transacao){
        return transacao;
        
    }
}
