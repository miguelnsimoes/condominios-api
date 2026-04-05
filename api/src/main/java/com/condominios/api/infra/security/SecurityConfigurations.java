package com.condominios.api.infra.security;


import com.condominios.api.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.condominios.api.usuario.UsuarioRole.ADM;

@Configuration // vai falar para o Spring que essa eh uma classe da configuracao
@EnableWebSecurity // aqui eu falo para o spring liberar a configuracao do websecurity


public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {   // aqui eu vou decidir os filtros para as minhas requisicoes
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //aqui vou ativar a autenticacao stateless, ou seja eu nao vou guardar a sessao do usuario, vou passar um token para ele
                .authorizeHttpRequests(authorize -> authorize   //agora vou fazer as autorizacoes  ||  aqui eu vou falar quais sao as minhas requisicoes http que eu quero que seja autenticadas

                        .requestMatchers(HttpMethod.GET, "/blocos", "/apartamentos", "/areas-comuns").hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")
                        .requestMatchers("/blocos", "/apartamentos", "/areas-comuns").hasRole("ADM")
                        .requestMatchers("/usuarios").hasRole("ADM") // ADM faz tudo com usuários
                        .requestMatchers(HttpMethod.GET, "/moradores").hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")
                        .requestMatchers(HttpMethod.PUT, "/moradores").hasAnyRole("ADM", "MORADOR") // Morador edita o próprio
                        .requestMatchers("/moradores").hasRole("ADM") // POST e DELETE sobra pro ADM
                        .requestMatchers(HttpMethod.GET, "/funcionarios").hasAnyRole("ADM", "FUNCIONARIO")
                        .requestMatchers("/funcionarios").hasRole("ADM") // O resto sobra pro ADM
                        .requestMatchers(HttpMethod.GET, "/encomendas").hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")
                        .requestMatchers(HttpMethod.POST, "/encomendas").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PUT, "/encomendas").hasAnyRole("ADM", "FUNCIONARIO")
                        .requestMatchers(HttpMethod.DELETE, "/encomendas").hasRole("ADM")
                        .requestMatchers(HttpMethod.GET, "/ocorrencias").hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")
                        .requestMatchers(HttpMethod.POST, "/ocorrencias").hasAnyRole("FUNCIONARIO", "MORADOR")
                        .requestMatchers(HttpMethod.PUT, "/ocorrencias").hasAnyRole("ADM", "FUNCIONARIO")
                        .requestMatchers(HttpMethod.DELETE, "/ocorrencias").hasRole("ADM")
                        .requestMatchers(HttpMethod.GET, "/reserva-area").hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")
                        .requestMatchers(HttpMethod.POST, "/reserva-area").hasAnyRole("ADM", "MORADOR")
                        .requestMatchers(HttpMethod.DELETE, "/reserva-area").hasAnyRole("ADM", "MORADOR")
                        .requestMatchers(HttpMethod.PUT, "/reserva-area").hasRole("ADM")
                        .requestMatchers(HttpMethod.GET, "/pagamentos").hasAnyRole("ADM", "MORADOR")
                        .requestMatchers("/pagamentos").hasRole("ADM") // POST, PUT e DELETE só ADM
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() //aqui eh o endpoint do login, por isso eu preciso liberar para todas as pessoas o acesso a esse endpoint(justamanete pq antes de ser autenticado, eh necessario fazer o login)
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .anyRequest().authenticated()
                )
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();



    }
    @Bean //aqui eu estou falando para o spring que esse authenticatorManager pode ser chamado por outras partes do codigo
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
