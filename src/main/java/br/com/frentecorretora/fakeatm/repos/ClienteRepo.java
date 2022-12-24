package br.com.frentecorretora.fakeatm.repos;

import org.springframework.data.repository.CrudRepository;

import br.com.frentecorretora.fakeatm.models.ClienteModel;

public interface ClienteRepo extends CrudRepository<ClienteModel, Long>{
    //public ClienteModel findByNome(String nome);
    
    //public ClienteModel findByCpf(String cpf);
}
