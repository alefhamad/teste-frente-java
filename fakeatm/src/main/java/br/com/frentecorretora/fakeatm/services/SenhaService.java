package br.com.frentecorretora.fakeatm.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SenhaService {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String senhaEncoder(String senha){
    String encodedPassword = encoder.encode(senha);
    return encodedPassword;
    }

    //create a class to validate the password
    public boolean senhaValida(String senha, String senhaEncoded){
        boolean senhaValida = encoder.matches(senha, senhaEncoded);
        return senhaValida;
    }
}