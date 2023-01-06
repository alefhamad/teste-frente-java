package br.com.frentecorretora.fakeatm.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.frentecorretora.fakeatm.models.ClienteModel;

public interface ClienteRepo extends CrudRepository<ClienteModel, Long> {
    
    ClienteModel findByConta(ClienteModel conta);

    Optional<ClienteModel> findByClienteCpf(String cpf);

    Boolean existsByClienteCpf(String cpf);

    ClienteModel findByContaIdConta(Long idConta);

    ClienteModel findClienteByClienteCpf(String cpf);

    ClienteModel deleteByIdCliente(Long id);

    ClienteModel deleteClienteByClienteCpf(String cpf);

}
