package com.condominios.api.reservaArea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservaAreaRepository extends JpaRepository<ReservaArea, Long> {

    boolean existsByAreaComumIdAndData(Long areaId, LocalDate data);
}
