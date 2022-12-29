package br.com.frentecorretora.fakeatm.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.frentecorretora.fakeatm.models.ContaModel;
import br.com.frentecorretora.fakeatm.models.PacoteModel;

public interface PacoteRepo extends CrudRepository<PacoteModel, Long>{
    


    //ArrayList<PacoteModel> findAllByConta(Long id);
    ArrayList<PacoteModel> findAllByConta(ContaModel conta);

}
