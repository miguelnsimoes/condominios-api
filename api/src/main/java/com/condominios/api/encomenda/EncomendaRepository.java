package com.condominios.api.encomenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {

    List<Encomenda> findByStatus(String status);

    void deleteByApartamento_Id(Long apartamentoId);
}
