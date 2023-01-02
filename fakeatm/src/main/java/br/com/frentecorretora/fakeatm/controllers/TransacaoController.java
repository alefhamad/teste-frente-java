package br.com.frentecorretora.fakeatm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frentecorretora.fakeatm.models.TransacaoModel;
import br.com.frentecorretora.fakeatm.repos.TransacaoRepo;
import br.com.frentecorretora.fakeatm.services.ContaService;
import br.com.frentecorretora.fakeatm.services.TipoDeNotaException;
import br.com.frentecorretora.fakeatm.services.TransacaoService;
import br.com.frentecorretora.fakeatm.services.ValorExcedidoException;

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
        try {
            transacaoService.criarTransacaoService(contaService.findByContaNumero(numero), transacao);
            return ResponseEntity.ok().body("Transação salva com sucesso!");
        } catch (ValorExcedidoException e) {
            return ResponseEntity.badRequest().body("Valor excedido!") ;
        } catch (TipoDeNotaException e) {
            return ResponseEntity.badRequest().body("Tipo de nota inválido!");
        }        
    }

    @GetMapping("/listar")
    public List<TransacaoModel> listaPacote (){
        return (ArrayList<TransacaoModel>) transacaoRepo.findAll();
    }

    
}
