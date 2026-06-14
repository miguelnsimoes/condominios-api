package com.condominios.api.reservaArea;

import com.condominios.api.infra.exception.BusinessException;
import com.condominios.api.infra.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaAreaService {

    private final ReservaAreaRepository reservaAreaRepository;

    public ReservaAreaService(ReservaAreaRepository reservaAreaRepository) {
        this.reservaAreaRepository = reservaAreaRepository;
    }

    public List<ReservaArea> getAll() {
        return reservaAreaRepository.findAll();
    }

    public ReservaArea findById(Long id) {
        return reservaAreaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva de área não encontrada"));
    }

    public ReservaArea save(ReservaArea reservaArea) {
        validarHorario(reservaArea);

        if (reservaAreaRepository.existsByAreaComumIdAndDataAndHoraInicioAndHoraFim(
                reservaArea.getAreaComum().getId(),
                reservaArea.getData(),
                reservaArea.getHoraInicio(),
                reservaArea.getHoraFim())) {
            throw new BusinessException("Já existe uma reserva para esta área, data e horário");
        }

        return reservaAreaRepository.save(reservaArea);
    }

    public void delete(Long id) {
        reservaAreaRepository.deleteById(id);
    }

    private void validarHorario(ReservaArea reservaArea) {
        if (reservaArea.getAreaComum() == null || reservaArea.getAreaComum().getId() == null) {
            throw new BusinessException("Área comum é obrigatória");
        }
        if (reservaArea.getMorador() == null || reservaArea.getMorador().getId() == null) {
            throw new BusinessException("Morador é obrigatório");
        }
        if (reservaArea.getData() == null) {
            throw new BusinessException("Data é obrigatória");
        }
        if (reservaArea.getHoraInicio() == null || reservaArea.getHoraFim() == null) {
            throw new BusinessException("Horário de início e fim são obrigatórios");
        }
        if (!reservaArea.getHoraFim().isAfter(reservaArea.getHoraInicio())) {
            throw new BusinessException("Horário de fim deve ser posterior ao horário de início");
        }
    }
}
