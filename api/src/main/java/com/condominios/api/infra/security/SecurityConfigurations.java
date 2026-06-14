package com.condominios.api.infra.security;

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

                        .requestMatchers(HttpMethod.GET, "/blocos", "/blocos/**",
                                "/apartamentos", "/apartamentos/**",
                                "/areas-comuns", "/areas-comuns/**")
                        .hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")
                        .requestMatchers(HttpMethod.POST, "/blocos", "/blocos/**",
                                "/apartamentos", "/apartamentos/**",
                                "/areas-comuns", "/areas-comuns/**")
                        .hasRole("ADM")
                        .requestMatchers(HttpMethod.PUT, "/blocos", "/blocos/**",
                                "/apartamentos", "/apartamentos/**",
                                "/areas-comuns", "/areas-comuns/**")
                        .hasRole("ADM")
                        .requestMatchers(HttpMethod.DELETE, "/blocos", "/blocos/**",
                                "/apartamentos", "/apartamentos/**",
                                "/areas-comuns", "/areas-comuns/**")
                        .hasRole("ADM")

                        .requestMatchers(HttpMethod.GET, "/moradores", "/moradores/**")
                        .hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")
                        .requestMatchers(HttpMethod.PUT, "/moradores", "/moradores/**")
                        .hasAnyRole("ADM", "MORADOR")
                        .requestMatchers(HttpMethod.POST, "/moradores", "/moradores/**")
                        .hasRole("ADM")
                        .requestMatchers(HttpMethod.DELETE, "/moradores", "/moradores/**")
                        .hasRole("ADM")

                        .requestMatchers(HttpMethod.GET, "/funcionarios", "/funcionarios/**")
                        .hasAnyRole("ADM", "FUNCIONARIO")
                        .requestMatchers("/funcionarios", "/funcionarios/**")
                        .hasRole("ADM")

                        .requestMatchers(HttpMethod.GET, "/encomendas", "/encomendas/**")
                        .hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")
                        .requestMatchers(HttpMethod.POST, "/encomendas", "/encomendas/**")
                        .hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PUT, "/encomendas", "/encomendas/**")
                        .hasAnyRole("ADM", "FUNCIONARIO")
                        .requestMatchers(HttpMethod.DELETE, "/encomendas", "/encomendas/**")
                        .hasRole("ADM")

                        .requestMatchers(HttpMethod.GET, "/ocorrencias", "/ocorrencias/**")
                        .hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")
                        .requestMatchers(HttpMethod.POST, "/ocorrencias", "/ocorrencias/**")
                        .hasAnyRole("FUNCIONARIO", "MORADOR")
                        .requestMatchers(HttpMethod.PUT, "/ocorrencias", "/ocorrencias/**")
                        .hasAnyRole("ADM", "FUNCIONARIO")
                        .requestMatchers(HttpMethod.DELETE, "/ocorrencias", "/ocorrencias/**")
                        .hasRole("ADM")

                        .requestMatchers(HttpMethod.GET, "/reservas-de-area", "/reservas-de-area/**")
                        .hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")
                        .requestMatchers(HttpMethod.POST, "/reservas-de-area", "/reservas-de-area/**")
                        .hasAnyRole("ADM", "MORADOR")
                        .requestMatchers(HttpMethod.DELETE, "/reservas-de-area", "/reservas-de-area/**")
                        .hasAnyRole("ADM", "MORADOR")
                        .requestMatchers(HttpMethod.PUT, "/reservas-de-area", "/reservas-de-area/**")
                        .hasRole("ADM")

                        .requestMatchers(HttpMethod.GET, "/pagamentos", "/pagamentos/**")
                        .hasAnyRole("ADM", "MORADOR")
                        .requestMatchers("/pagamentos", "/pagamentos/**")
                        .hasRole("ADM")

                        .requestMatchers(HttpMethod.GET, "/relatorios", "/relatorios/**")
                        .hasAnyRole("ADM", "FUNCIONARIO", "MORADOR")

                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register")
                        .permitAll()

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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
