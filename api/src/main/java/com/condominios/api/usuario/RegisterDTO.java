package com.condominios.api.usuario;

public record RegisterDTO(String login, String senha, UsuarioRole role) {
}
