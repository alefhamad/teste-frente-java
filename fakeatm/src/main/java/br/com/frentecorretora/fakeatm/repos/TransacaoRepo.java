package br.com.frentecorretora.fakeatm.repos;

import org.springframework.data.repository.CrudRepository;

import br.com.frentecorretora.fakeatm.models.TransacaoModel;

public interface TransacaoRepo extends CrudRepository<TransacaoModel, Long>{
    
}
