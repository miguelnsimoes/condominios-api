package com.condominios.api.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //utilizado para o spring identificar que essa classe reprensenta um servico da aplicacao.
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository repository; //dessa forma eu posso criar um objeto que seja uma interface e colocar na variavel automaticamente

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //vai ser o lugar que vamos fazer a consulta dos nossos usuario para o spring security
        return repository.findByLogin(username);
    }

}
