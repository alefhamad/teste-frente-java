package br.com.frentecorretora.fakeatm.repos;

import org.springframework.data.repository.CrudRepository;

import br.com.frentecorretora.fakeatm.models.ClienteModel;

public interface ClienteRepo extends CrudRepository<ClienteModel, Long> {
    
    ClienteModel findByConta(ClienteModel conta);

    ClienteModel findByClienteCpf(String cpf);

    ClienteModel findByContaIdConta(Long idConta);

    ClienteModel findClienteByClienteCpf(String cpf);
}
