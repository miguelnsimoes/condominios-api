package com.condominios.api.infra.security;


import com.condominios.api.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // para falar para o spring que essa classe eh um controller rest
@RequestMapping("auth") //aqui eu falo para o spring qual endpoint esse controller vai ser chamado

public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login") // aqui eu falo q eh um endpoint do tipo Post, em que o usuario vai fazer um login.
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
      var usuarioSenha = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
      var auth = this.authenticationManager.authenticate(usuarioSenha);

      var token = tokenService.generateToken((Usuario)auth.getPrincipal());

      return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) { //aqui eu utilizo registerDTO, pq na classe record authentication dto, eu nao passo a role, apenas login e senha.
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();  //essa linha eh utilizada para verificar se o nome do usuario que vai ser registrado ja existe no banco de dados, caso ja existe ele responde com um badrequest.

         String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());  //aqui a senha foi criptografada utilizando o algoritmo hash blowfish
         Usuario newUser = new Usuario(data.login(), encryptedPassword, data.role());  //aqui eu estou criando/registrando o usuario novo, passando o login, senha criptografada e a role desse usuario.

            this.repository.save(newUser);

            return ResponseEntity.ok().build();

    }
}
