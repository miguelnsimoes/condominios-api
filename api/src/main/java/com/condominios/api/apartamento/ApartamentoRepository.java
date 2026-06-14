package com.condominios.api.apartamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {
    List<Apartamento> findByBlocoId(Long blocoId);

    boolean existsByNumeroAndBlocoId(String numero, Long blocoId);

    boolean existsByNumeroAndBlocoIdAndIdNot(String numero, Long blocoId, Long id);
}
