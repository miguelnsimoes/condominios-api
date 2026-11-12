package com.condominios.api.morador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long> {

    List<Morador> findByApartamentoId(Long apartamentoId);

    Optional<Morador> findByUsuario_Id(Long usuarioId);

    Optional<Morador> findByCpf(String cpf);
}
