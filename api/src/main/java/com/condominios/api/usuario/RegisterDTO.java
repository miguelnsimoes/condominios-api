package com.condominios.api.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotBlank String login,
        @NotBlank String senha,
        @NotNull UsuarioRole role,
        String cpf
) {
}
