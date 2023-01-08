package br.com.frentecorretora.fakeatm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frentecorretora.fakeatm.dto.OperacaoDto;
import br.com.frentecorretora.fakeatm.models.TransacaoModel;
import br.com.frentecorretora.fakeatm.services.AuthService;
import br.com.frentecorretora.fakeatm.services.ContaService;
import br.com.frentecorretora.fakeatm.services.TipoDeNotaException;
import br.com.frentecorretora.fakeatm.services.TransacaoService;
import br.com.frentecorretora.fakeatm.services.ValorExcedidoException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private AuthService auth;

    @PostMapping("/criar")
    public ResponseEntity<String> criarTransacao(@RequestBody OperacaoDto operacaoDto) {
        if (Boolean.FALSE.equals(auth.isConta(operacaoDto.getConta()))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Essa não parece ser a sua conta amigão!!!");
        }
        try {
            TransacaoModel transacao = new TransacaoModel(); 
            transacao.setValor(operacaoDto.getValor());
            transacao.setTipoDeNota(operacaoDto.getNota());
            transacaoService.criarTransacaoService(contaService.findByContaNumero(operacaoDto.getConta()), transacao);
            return ResponseEntity.ok().body("Transação salva com sucesso!");
        } catch (ValorExcedidoException e) {
            return ResponseEntity.badRequest().body("Valor excedido!");
        } catch (TipoDeNotaException e) {
            return ResponseEntity.badRequest().body("Tipo de nota inválido!");
        }
    }
}
