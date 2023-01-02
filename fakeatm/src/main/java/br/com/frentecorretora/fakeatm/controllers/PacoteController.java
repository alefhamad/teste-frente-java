package br.com.frentecorretora.fakeatm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frentecorretora.fakeatm.models.ContaModel;
import br.com.frentecorretora.fakeatm.models.PacoteModel;
import br.com.frentecorretora.fakeatm.repos.ContaRepo;
import br.com.frentecorretora.fakeatm.repos.PacoteRepo;
import br.com.frentecorretora.fakeatm.services.PacoteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pacote")
public class PacoteController {    

    @Autowired 
    private PacoteRepo pacoteRepo;


    @Autowired
    private PacoteService pacoteService;

    @Autowired
    private ContaRepo contaRepo;

    public class CriaPacoteRequest {

        private ContaModel contaModel;

        public ContaModel getContaModel() {
            return contaModel;
        }

        public void setContaModel(ContaModel contaModel) {
            this.contaModel = contaModel;
        }

    }

    @PostMapping("/criar/{numero}")
    public ResponseEntity<String> criarPacote(@PathVariable("numero") String numero) {
            ContaModel conta = contaRepo.findByContaNumero(numero);
            
            PacoteModel pacoteSalvo = pacoteService.criarPacoteServiceVazio(conta);
            if(pacoteSalvo != null){
                return ResponseEntity.ok().body("Pacote criado com sucesso!");
            }
            return ResponseEntity.badRequest().body("Parece que você tem pacotes abertos de mais!");
            
    }

    @GetMapping("/listar")
    public List<PacoteModel> pegaPacotes(){
        return (ArrayList<PacoteModel>) pacoteRepo.findAll();
    }

    @GetMapping("/listaUltimo/{numero}")
    public PacoteModel pegaUltimoPacote(@PathVariable("numeroj") String numero){
        return pacoteService.listaUltimoPacoteDaConta(numero);
    }
}
