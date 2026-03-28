package com.condominios.api.reservaArea;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaAreaService {
    private final ReservaAreaRepository reservaAreaRepository;

    public ReservaAreaService(ReservaAreaRepository reservaAreaRepository) {
        this.reservaAreaRepository = reservaAreaRepository;
    }

    public List<ReservaArea> getAll(){
        return reservaAreaRepository.findAll();
    }

    public ReservaArea findById(Long id){
        return reservaAreaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva de área nao encontrado"));
    }

    public ReservaArea save(ReservaArea reservaArea){
        if (reservaAreaRepository.existsByAreaComumIdAndData(reservaArea.getAreaComum().getId(), reservaArea.getData())){
            throw new RuntimeException("Ja existe uma reserva nesse dia!");
        }
        else{
            return reservaAreaRepository.save(reservaArea);
        }
    }

    public void delete(Long id){
        reservaAreaRepository.deleteById(id);
    }

}
