package com.condominios.api.relatorio;

import com.condominios.api.encomenda.Encomenda;
import com.condominios.api.encomenda.EncomendaRepository;
import com.condominios.api.ocorrencia.Ocorrencia;
import com.condominios.api.ocorrencia.OcorrenciaRepository;
import com.condominios.api.pagamento.Pagamento;
import com.condominios.api.pagamento.PagamentoRepository;
import com.condominios.api.reservaArea.ReservaArea;
import com.condominios.api.reservaArea.ReservaAreaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    private static final String STATUS_PENDENTE = "PENDENTE";

    private final EncomendaRepository encomendaRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ReservaAreaRepository reservaAreaRepository;
    private final OcorrenciaRepository ocorrenciaRepository;

    public RelatorioService(
            EncomendaRepository encomendaRepository,
            PagamentoRepository pagamentoRepository,
            ReservaAreaRepository reservaAreaRepository,
            OcorrenciaRepository ocorrenciaRepository) {
        this.encomendaRepository = encomendaRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.reservaAreaRepository = reservaAreaRepository;
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    public List<Encomenda> encomendasPendentes() {
        return encomendaRepository.findByStatus(STATUS_PENDENTE);
    }

    public List<Pagamento> pagamentosRealizados() {
        return pagamentoRepository.findByDataPagamentoIsNotNull();
    }

    public List<ReservaArea> reservasAreasComuns() {
        return reservaAreaRepository.findAll();
    }

    public List<Ocorrencia> ocorrenciasRegistradas() {
        return ocorrenciaRepository.findAll();
    }
}
