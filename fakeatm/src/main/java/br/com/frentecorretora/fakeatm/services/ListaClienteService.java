package br.com.frentecorretora.fakeatm.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frentecorretora.fakeatm.models.ClienteModel;
import br.com.frentecorretora.fakeatm.repos.ClienteRepo;

@Service
public class ListaClienteService {
    
    @Autowired
    private ClienteRepo clienteRepo;

    public ArrayList<ClienteModel> listaClientes() {
        ArrayList<ClienteModel> listaClientes = (ArrayList<ClienteModel>) clienteRepo.findAll();
        return listaClientes;
    }
}
