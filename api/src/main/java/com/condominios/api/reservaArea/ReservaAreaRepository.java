package com.condominios.api.reservaArea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ReservaAreaRepository extends JpaRepository<ReservaArea, Long> {

    boolean existsByAreaComumIdAndDataAndHoraInicioAndHoraFim(
            Long areaId, LocalDate data, LocalTime horaInicio, LocalTime horaFim);

    void deleteByMorador_Id(Long moradorId);
}
