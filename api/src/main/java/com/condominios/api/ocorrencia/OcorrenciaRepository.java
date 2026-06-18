package com.condominios.api.ocorrencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

    void deleteByMorador_Id(Long moradorId);

    List<Ocorrencia> findByMorador_Id(Long moradorId);
}