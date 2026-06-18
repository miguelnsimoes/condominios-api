package com.condominios.api.infra.security;


import com.condominios.api.morador.Morador;
import com.condominios.api.morador.MoradorRepository;
import com.condominios.api.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")

public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MoradorRepository moradorRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
      var usuarioSenha = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
      var auth = this.authenticationManager.authenticate(usuarioSenha);

      var usuario = (Usuario) auth.getPrincipal();
      var token = tokenService.generateToken(usuario);

      Long moradorId = null;
      Long apartamentoId = null;
      var moradorOpt = moradorRepository.findByUsuario_Id(usuario.getId());
      if (moradorOpt.isPresent()) {
          Morador morador = moradorOpt.get();
          moradorId = morador.getId();
          if (morador.getApartamento() != null) {
              apartamentoId = morador.getApartamento().getId();
          }
      }

      return ResponseEntity.ok(new LoginResponseDTO(token, usuario.getRole().name(), moradorId, apartamentoId));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

         String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
         Usuario newUser = new Usuario(data.login(), encryptedPassword, data.role());

            this.repository.save(newUser);

            if (data.role() == UsuarioRole.MORADOR && data.cpf() != null && !data.cpf().isBlank()) {
                moradorRepository.findByCpf(data.cpf().trim()).ifPresent(morador -> {
                    morador.setUsuario(newUser);
                    moradorRepository.save(morador);
                });
            }

            return ResponseEntity.ok().build();

    }
}
