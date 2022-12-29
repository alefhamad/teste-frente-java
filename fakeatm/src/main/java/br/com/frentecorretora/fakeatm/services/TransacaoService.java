package br.com.frentecorretora.fakeatm.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frentecorretora.fakeatm.models.TransacaoModel;
import br.com.frentecorretora.fakeatm.repos.TransacaoRepo;

@Service
public class TransacaoService {

    @Autowired 
    private TransacaoRepo transacaoRepo;

    public TransacaoModel salvarTransacaoService(TransacaoModel transacao) {
        
        
        TransacaoModel novaTransacao = transacaoRepo.save(transacao);
        transacao = novaTransacao;
        return transacao;

    }
    
    //public TransacaoModel listarTransacoes()
    public TransacaoModel criarTransacaoService(TransacaoModel transacao) {
        
        Instant dataAgora = Instant.now();
        TransacaoModel novaTransacao = new TransacaoModel(0, dataAgora, transacao.getPacote());
        transacao.getValor();

        return transacao;
    }
}
