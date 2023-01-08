package br.com.frentecorretora.fakeatm.services;

import java.util.List;
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

    @Autowired
    private ClienteService clienteService;

    public PacoteModel criarPacoteService(ContaModel conta) {
        PacoteModel novoPacote = new PacoteModel(conta);
        pacoteRepo.save(novoPacote);
        return novoPacote;
    }

    public List<PacoteModel> listaPacotes() {
        return pacoteRepo.findAllByConta(
                contaRepo.findByContaNumero(clienteService.retornaClienteContexto().getConta().getContaNumero()));
    }

    public PacoteModel listaUltimoPacoteDaConta(){      
        return pacoteRepo.findTopByContaOrderByIdPacoteDesc(contaRepo.findByContaNumero(clienteService.retornaClienteContexto().getConta().getContaNumero()));
    }

    public boolean statusUltimoPacote(String numeroConta) {
        PacoteModel pacote = pacoteRepo.findTopByContaOrderByIdPacoteDesc(contaRepo.findByContaNumero(numeroConta));
        return pacote == null || pacote.getStatusPacote().equals("Fechado");
    }

    public PacoteModel criarPacoteServiceVazio(){
        ContaModel conta = clienteService.retornaClienteContexto().getConta();
        boolean statusPacote = statusUltimoPacote(conta.getContaNumero());
        if(!statusPacote){
            return null;
        }
        PacoteModel novoPacote = new PacoteModel(conta);
        pacoteRepo.save(novoPacote);
        return novoPacote;
    }
}
