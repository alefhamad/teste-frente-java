package br.com.frentecorretora.fakeatm.services;

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

    // public TransacaoModel listarTransacoes()
    public TransacaoModel criarTransacaoService(ContaModel conta, TransacaoModel transacao) {
        boolean statusPacote = pacoteService.statusUltimoPacote(conta.getIdConta());

        double valor = transacao.getValor();
        double tipoDeNota = transacao.getTipoDeNota();
        double limiteDeNota = transacao.getLimiteDeNotas();
        double limiteValor = transacao.getLimiteDeValor();
        double valorPorNota = valor / tipoDeNota;
        double qtdDeTransacoes = valorPorNota / limiteDeNota;
        System.out.println("aqui1");
        System.out.println(valorPorNota);
        if (statusPacote && qtdDeTransacoes <= 1) {
            PacoteModel newPacote = pacoteService.criarPacoteService(conta);
            newPacote.setStatusPacote("Fechado");
            transacao.setQuantidadeDeNotas(valorPorNota);
            TransacaoModel newTransacao = salvarTransacaoService(newPacote, transacao);
            System.err.println("aqui2");
            return newTransacao;
        }
        
        double limitePorPacote = 0; //Poderia ser um Enum.
            if(tipoDeNota == 10.00){
                limitePorPacote = 500.00;
            }
            if(tipoDeNota == 50.00){
                limitePorPacote = 2500.00;
            }
            if(tipoDeNota == 100.00){
                limitePorPacote = 5000.00;
            }

        PacoteModel newPacote = pacoteService.criarPacoteService(conta);
        while(valor > 0){

            double reduz = limitePorPacote;
            newPacote.setStatusPacote("Fechado");
            transacao.setValor(reduz);
            transacao.setQuantidadeDeNotas(valorPorNota);
            System.out.println(valorPorNota);
            TransacaoModel newTransacao = salvarTransacaoService(newPacote, transacao);
            valor -= limitePorPacote;
            System.out.println("aqui3");
            
        }
        return transacao;

    }

    public TransacaoModel criarTransacaoRecursiva(TransacaoModel transacao, PacoteModel pacote) {
        return transacao;

    }

    public TransacaoModel salvarTransacaoService(PacoteModel pacote, TransacaoModel transacao) {
        TransacaoModel newTransacao = new TransacaoModel(transacao.getValor(), transacao.getTipoDeNota(), null, pacote);
        transacaoRepo.save(newTransacao);
        return newTransacao;

    }
}
