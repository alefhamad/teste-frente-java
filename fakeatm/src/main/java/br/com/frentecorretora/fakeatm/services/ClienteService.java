package br.com.frentecorretora.fakeatm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frentecorretora.fakeatm.dto.UsuarioDto;
import br.com.frentecorretora.fakeatm.models.ClienteModel;
import br.com.frentecorretora.fakeatm.models.ContaModel;
import br.com.frentecorretora.fakeatm.repos.ClienteRepo;
import br.com.frentecorretora.fakeatm.repos.ContaRepo;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private ContaRepo contaRepo;

    @Autowired
    private GeraContaService contaService;

    // auto wire the SenhaService class
    @Autowired
    private SenhaService senhaService;

    public ClienteModel salvarClienteService(ClienteModel cliente) {

        if (validaClienteModel(cliente).equals("Cpf inválido")) {
            return null;
        }
        cliente.setClienteSenha(senhaService.senhaEncoder(cliente.getClienteSenha()));
        clienteRepo.save(cliente);
        ContaModel conta = new ContaModel(contaService.geraRandomUnico(), cliente);
        conta = contaRepo.save(conta);
        cliente.setClienteSenha("**********");
        cliente.setConta(conta);
        return cliente;

    }

    public ClienteModel deleteClientById(Long id) {

        clienteRepo.deleteById(id);
        return null;
    }

    public String validaClienteModel(ClienteModel cliente) {
        String resposta = "Cpf válido";

        if (!validaCpf(cliente.getClienteCpf())) {
            resposta = "Cpf inválido";
            return resposta;
        }
        return resposta;
    }

    public String retornaSenha(ClienteModel cliente) {
        return clienteRepo.findClienteByClienteCpf(cliente.getClienteCpf()).getClienteSenha();
    }

    public ClienteModel retornaClienteContexto() {
        if(AuthService.returnPrincipal() == null) {
            return null;
        }
        return clienteRepo.findClienteByClienteCpf(AuthService.returnPrincipal());
    }

    public ClienteModel atualizaCliente(UsuarioDto usuario) {
        ClienteModel clienteModel = clienteRepo.findClienteByClienteCpf(usuario.getUsername());
        clienteModel.setClienteNome(usuario.getName());
        clienteModel.setClienteSenha(senhaService.senhaEncoder(usuario.getPassword()));
        clienteModel.setClienteEndereco(usuario.getEndereco());
        clienteRepo.save(clienteModel);
        return clienteModel;
    }

    public boolean validaCpf(String cpf) {
        // remove all non-digit characters
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            return false;
        }
        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999")) {
            return false;
        }
        int soma = 0;
        int resto = 0;

        for (int i = 1; i <= 9; i++) {
            soma = soma + Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) {
            resto = 0;
        }
        if (resto != Integer.parseInt(cpf.substring(9, 10))) {
            return false;
        }
        soma = 0;
        for (int i = 1; i <= 10; i++) {
            soma = soma + Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) {
            resto = 0;
        }
        if (resto != Integer.parseInt(cpf.substring(10, 11))) {
            return false;
        }

        // if cpf exists in database, return false
        if (clienteRepo.findClienteByClienteCpf(cpf) != null) {
            return false;
        }
        return true;
    }
}
