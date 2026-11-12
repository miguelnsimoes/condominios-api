package com.condominios.api.usuario;

public record LoginResponseDTO(
        String token,
        String role,
        Long moradorId,
        Long apartamentoId
) {
}
