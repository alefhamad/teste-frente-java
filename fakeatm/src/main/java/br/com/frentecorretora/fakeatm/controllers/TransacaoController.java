package br.com.frentecorretora.fakeatm.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frentecorretora.fakeatm.models.ContaModel;
import br.com.frentecorretora.fakeatm.models.TransacaoModel;
import br.com.frentecorretora.fakeatm.repos.TransacaoRepo;
import br.com.frentecorretora.fakeatm.services.TransacaoService;

@RestController 
@CrossOrigin("*")
@RequestMapping("/api/transacao")
public class TransacaoController {

    @Autowired 
    private TransacaoRepo transacaoRepo;

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/criar")
    public ResponseEntity<TransacaoModel> criarTransacao(@RequestBody ContaModel conta) {
        TransacaoModel transacaoSalva = transacaoService.criarTransacaoService(conta);
        return ResponseEntity.ok(transacaoSalva);
    }

    @GetMapping("/listar")
    private ArrayList<TransacaoModel> listaPacote (){
        ArrayList<TransacaoModel> listaTransacoes = (ArrayList<TransacaoModel>) transacaoRepo.findAll();
        return listaTransacoes;
    }

    
}
