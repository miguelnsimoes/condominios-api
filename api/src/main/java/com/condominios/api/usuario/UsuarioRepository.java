package com.condominios.api.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> { // ao extender JpaRepository eu ganho metodos para salvar no banco, buscar id, lista todos e deleta
       UserDetails findByLogin(String login); // vem do spring security e eh utilizado
                                              // para autenticar um usuario

}
