package br.com.frentecorretora.fakeatm.services;

import java.time.Instant;

import org.apache.tomcat.util.bcel.Const;
import org.hibernate.query.criteria.internal.compile.CriteriaInterpretation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frentecorretora.fakeatm.models.ContaModel;
import br.com.frentecorretora.fakeatm.models.PacoteModel;
import br.com.frentecorretora.fakeatm.models.TransacaoModel;
import br.com.frentecorretora.fakeatm.repos.TransacaoRepo;

@Service
public class TransacaoService {

    @Autowired 
    private TransacaoRepo transacaoRepo;

    @Autowired
    private PacoteService pacoteService;


    public TransacaoModel salvarTransacaoService(TransacaoModel transacao) {
        
        
        TransacaoModel novaTransacao = transacaoRepo.save(transacao);
        transacao = novaTransacao;
        return transacao;

    }
    
    //public TransacaoModel listarTransacoes()
    public TransacaoModel criarTransacaoService(TransacaoModel transacao) {
        
        PacoteModel pacote = transacao.getPacote();
        //ContaModel conta = pacote.getConta();
        double limiteValor = 5000;

        double limiteNota = transacao.getValor() / transacao.getTipoDeNota();
        
        if(transacao.getValor() >= limiteValor || limiteNota >= 50){
            while(transacao.getValor() > limiteValor || limiteNota >= 50){
                double resto = transacao.getValor() - limiteValor;
                transacao.setValor(limiteValor);
                salvarTransacaoService(transacao);
                PacoteModel novoPacote = pacoteService.criarPacoteService(transacao.getPacote());
                transacao.setPacote(novoPacote);
                transacao.setValor(resto);
            }
            salvarTransacaoService(transacao);
        }
    


        return transacao;
    }


}
