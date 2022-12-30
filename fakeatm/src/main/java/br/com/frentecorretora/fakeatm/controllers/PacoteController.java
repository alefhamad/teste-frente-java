package br.com.frentecorretora.fakeatm.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frentecorretora.fakeatm.models.ContaModel;
import br.com.frentecorretora.fakeatm.models.PacoteModel;
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

    public class CriaPacoteRequest {

        private ContaModel contaModel;

        public ContaModel getContaModel() {
            return contaModel;
        }

        public void setContaModel(ContaModel contaModel) {
            this.contaModel = contaModel;
        }

    }

    @PostMapping("/criar")
    public ResponseEntity<PacoteModel> criarPacote(@RequestBody ContaModel conta) {
            PacoteModel pacoteSalvo = pacoteService.criarPacoteService(conta);
            return ResponseEntity.ok(pacoteSalvo);
    }

    @GetMapping("/listar")
    public ArrayList<PacoteModel> pegaPacotes(){
        ArrayList<PacoteModel> listaPacotes = (ArrayList<PacoteModel>) pacoteRepo.findAll();
        return listaPacotes;
    }

    @GetMapping("/listaUltimo/{id}")
    public PacoteModel pegaUltimoPacote(@PathVariable("id") Long id){
        PacoteModel ultimoPacote = pacoteService.listaUltimoPacoteDaConta(id);
        return ultimoPacote;
    }
}
