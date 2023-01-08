package br.com.frentecorretora.fakeatm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frentecorretora.fakeatm.models.PacoteModel;
import br.com.frentecorretora.fakeatm.services.PacoteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pacote")
public class PacoteController {    

    @Autowired
    private PacoteService pacoteService;

    @PostMapping("/criar")
    public ResponseEntity<String> criarPacote() {
            PacoteModel pacoteSalvo = pacoteService.criarPacoteServiceVazio();
            if(pacoteSalvo != null){
                return ResponseEntity.ok().body("Pacote criado com sucesso!");
            }
            return ResponseEntity.badRequest().body("Parece que você tem pacotes abertos de mais!");
            
    }

    @GetMapping("/lista/ultimo")
    public PacoteModel pegaUltimoPacote(){
        return pacoteService.listaUltimoPacoteDaConta();
    }

    @GetMapping("/lista/todos")
    public List<PacoteModel> listarPacotes(){
        return pacoteService.listaPacotes();
    }
}
