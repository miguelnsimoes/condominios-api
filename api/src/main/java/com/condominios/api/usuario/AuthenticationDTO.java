package com.condominios.api.usuario;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank String login,
        @NotBlank String senha
) {
}
