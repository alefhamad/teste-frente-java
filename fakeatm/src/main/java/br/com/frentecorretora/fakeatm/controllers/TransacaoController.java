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
import br.com.frentecorretora.fakeatm.models.TransacaoModel;
import br.com.frentecorretora.fakeatm.repos.TransacaoRepo;
import br.com.frentecorretora.fakeatm.services.ContaService;
import br.com.frentecorretora.fakeatm.services.TransacaoService;

@RestController 
@CrossOrigin("*")
@RequestMapping("/api/transacao")
public class TransacaoController {

    @Autowired 
    private TransacaoRepo transacaoRepo;

    @Autowired
    private TransacaoService transacaoService;

    @Autowired 
    private ContaService contaService;

    @PostMapping("/criar/conta={numero}")
    public ResponseEntity<String> criarTransacao(@PathVariable("numero") String numero, @RequestBody TransacaoModel transacao) {
        if(transacao.getValor()>5000){
        return ResponseEntity.badRequest().body("Não faça transações maiores que R$5000.00");
        }
        ContaModel conta = contaService.findByContaNumero(numero);
        TransacaoModel transacaoSalva = transacaoService.criarTransacaoService(conta, transacao);
        return ResponseEntity.ok().body("Transação salva com sucesso!");
    }

    @GetMapping("/listar")
    private ArrayList<TransacaoModel> listaPacote (){
        ArrayList<TransacaoModel> listaTransacoes = (ArrayList<TransacaoModel>) transacaoRepo.findAll();
        return listaTransacoes;
    }

    
}
