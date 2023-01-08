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

    public TransacaoModel criarTransacaoService(ContaModel conta, TransacaoModel transacao)
            throws ValorExcedidoException, TipoDeNotaException {

        boolean statusPacote = pacoteService.statusUltimoPacote(conta.getContaNumero());

        double valor = transacao.getValor();
        double tipoDeNota = transacao.getTipoDeNota();
        double limiteDeNota = transacao.getLimiteDeNotas();
        double valorPorNota = valor / tipoDeNota;
        double qtdDeTransacoes = valorPorNota / limiteDeNota;

        double limitePorPacote = 0; // Poderia ser um Enum.
        if (tipoDeNota == 10.00) {
            limitePorPacote = 500.00;
        }
        if (tipoDeNota == 50.00) {
            limitePorPacote = 2500.00;
        }
        if (tipoDeNota == 100.00) {
            limitePorPacote = 5000.00;
        }

        if (valor > 5000.00) {
            throw new ValorExcedidoException("Valor não pode ser maior que R$5000.00");
        }
        if (valor % tipoDeNota != 0) {
            throw new TipoDeNotaException("Apenas, valores compatível com 10, 50 e 100");
        }

        

        if (statusPacote && qtdDeTransacoes <= 1) {
            PacoteModel newPacote = pacoteService.criarPacoteService(conta);
            transacao.setQuantidadeNotasUtilizadas((int) (valor / tipoDeNota));
            transacao.setStatusTransacao("Finalizada");
            transacao.setLimiteDeValor((int) (limitePorPacote));
            newPacote.setDataFechamento();
            newPacote.setStatusPacote("Fechado");
            return salvarTransacaoService(newPacote, transacao);
        }

        /*
         * Para criar as transações no mesmo pacote, basta jogar a linha 63 para linha
         * 60;
         */

        while (valor > 0) {

            PacoteModel newPacote = pacoteService.criarPacoteService(conta);
            double reduz = limitePorPacote;
            if (valor < limitePorPacote) {
                reduz = valor;
            }

            transacao.setValor(reduz);
            transacao.setQuantidadeNotasUtilizadas((int) (reduz / tipoDeNota));
            transacao.setStatusTransacao("Finalizada");
            transacao.setLimiteDeValor((int) (limitePorPacote));
            newPacote.setDataFechamento();
            newPacote.setStatusPacote("Fechado");

            salvarTransacaoService(newPacote, transacao);
            valor -= limitePorPacote;
        }
        return transacao;

    }

    public TransacaoModel salvarTransacaoService(PacoteModel pacote, TransacaoModel transacao) {
        TransacaoModel newTransacao = new TransacaoModel(transacao.getValor(), transacao.getTipoDeNota(),
                transacao.getStatusTransacao(), transacao.getQuantidadeNotasUtilizadas(), transacao.getLimiteDeValor(),
                pacote);
        transacaoRepo.save(newTransacao);
        return newTransacao;

    }
}
