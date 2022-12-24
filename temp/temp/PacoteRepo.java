package br.com.frentecorretora.fakeatm.repos;

import org.springframework.data.repository.CrudRepository;

import br.com.frentecorretora.fakeatm.models.PacoteModel;

public interface PacoteRepo extends CrudRepository<PacoteModel, Long>{
    
}
