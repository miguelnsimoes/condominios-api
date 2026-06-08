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

@Configuration
@EnableWebSecurity


public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        .requestMatchers(HttpMethod.GET, "/blocos", "/apartamentos", "/areas-comuns").hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")
                        .requestMatchers("/blocos", "/apartamentos", "/areas-comuns").hasRole("ADM")
                        .requestMatchers("/usuarios").hasRole("ADM")
                        .requestMatchers(HttpMethod.GET, "/moradores").hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")
                        .requestMatchers(HttpMethod.PUT, "/moradores").hasAnyRole("ADM", "MORADOR")
                        .requestMatchers("/moradores").hasRole("ADM")
                        .requestMatchers(HttpMethod.GET, "/funcionarios").hasAnyRole("ADM", "FUNCIONARIO")
                        .requestMatchers("/funcionarios").hasRole("ADM")
                        .requestMatchers(HttpMethod.GET, "/relatorios/**").hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")
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
                        .requestMatchers("/pagamentos").hasRole("ADM")
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .anyRequest().authenticated()
                )
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();



    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
