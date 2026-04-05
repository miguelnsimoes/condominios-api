package com.condominios.api.usuario;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Table(name = "usuario")
@Entity(name = "usuario")


public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //aqui eu estou passando para o meu codigo como ele deve gerar o meu ID
    private Long id;

    private String login;
    private String senha;
    @Enumerated(EnumType.STRING)// por padrao o java utilizar ordinal, ou seja as minhas roles vao estar em ordem numerica de chegada
    //adicionando o enumerated, eu faco com que ao contrario de adm = 2, adm sera "adm", util para a persistencia do banco de dados.
    private UsuarioRole role;


    public Usuario(String login, String senha, UsuarioRole role) { //esse construtor eh utilizado ara quando eu for registrar o usuario, pois eu nao preciso de id nessa hora
        this.login = login;
        this.senha = senha;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + this.role.name()));
        //eu utilizei essa formatacao de "ROLE_" + para q eu nao precise colocoar role em todos os meus cargos
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    public String getUsername() {
        return this.login;
    }

    public Usuario() {}

    public Usuario(Long id, String login, String senha, UsuarioRole role) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.role = role;
    }

    //toda essa parte eh utilizada para verificar se o usuario esta com a conta
    //fechada, expirada, fechada ou se esta enable para ser autenticada, para simplificar
    // no momento vou deixar todos os retornos true, depois vou fazer a logica verdadeira de autenticacao.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public  UsuarioRole getRole() {
        return role;
    }

    public void setRole(UsuarioRole role) {
        this.role = role;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }


}
