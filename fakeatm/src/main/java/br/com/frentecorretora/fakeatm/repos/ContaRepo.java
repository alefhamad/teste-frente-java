package br.com.frentecorretora.fakeatm.repos;

import org.springframework.data.repository.CrudRepository;

import br.com.frentecorretora.fakeatm.models.ContaModel;

public interface ContaRepo extends CrudRepository<ContaModel, Long> {
    
    public ContaModel findById(long id);

    public ContaModel findByContaNumero(String contaNumero);
}
