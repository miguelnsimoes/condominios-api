package com.condominios.api.infra.security;

import com.condominios.api.infra.exception.BusinessException;
import com.condominios.api.morador.Morador;
import com.condominios.api.morador.MoradorRepository;
import com.condominios.api.usuario.Usuario;
import com.condominios.api.usuario.UsuarioRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    private final MoradorRepository moradorRepository;

    public SecurityUtils(MoradorRepository moradorRepository) {
        this.moradorRepository = moradorRepository;
    }

    public Usuario getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof Usuario usuario)) {
            throw new BusinessException("Usuário não autenticado");
        }
        return usuario;
    }

    public boolean isAdm() {
        return getAuthenticatedUser().getRole() == UsuarioRole.ADM;
    }

    public boolean isMorador() {
        return getAuthenticatedUser().getRole() == UsuarioRole.MORADOR;
    }

    public boolean isFuncionario() {
        return getAuthenticatedUser().getRole() == UsuarioRole.FUNCIONARIO;
    }

    public Morador getMoradorLogado() {
        return moradorRepository.findByUsuario_Id(getAuthenticatedUser().getId())
                .orElse(null);
    }

    public Long getMoradorIdLogado() {
        Morador morador = getMoradorLogado();
        return morador != null ? morador.getId() : null;
    }

    public void validarAcessoMorador(Long moradorId) {
        if (isAdm() || isFuncionario()) return;
        Long ownId = getMoradorIdLogado();
        if (ownId == null || !ownId.equals(moradorId)) {
            throw new BusinessException("Acesso negado a dados de outro morador");
        }
    }
}
