package br.com.frentecorretora.fakeatm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SenhaService {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String senhaEncoder(String senha){
    String encodedPassword = encoder.encode(senha);
    return encodedPassword;
    }


}
