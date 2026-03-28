package com.condominios.api.reservaArea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaAreaRepository extends JpaRepository<ReservaArea, Long> {
}
