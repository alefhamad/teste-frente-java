package br.com.frentecorretora.fakeatm.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.frentecorretora.fakeatm.models.ClienteModel;
import br.com.frentecorretora.fakeatm.repos.ClienteRepo;

@Service
public class ClienteUserDetailsService implements UserDetailsService{
    
    private ClienteRepo clienteRepo;

    public ClienteUserDetailsService(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        ClienteModel cliente = clienteRepo.findByClienteCpf(cpf)
        .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado"));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        

        return new org.springframework.security.core.userdetails.User(cliente.getClienteCpf(), cliente.getClienteSenha(), authorities);
    }
}

