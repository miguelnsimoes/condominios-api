package com.condominios.api.relatorio;

import com.condominios.api.encomenda.Encomenda;
import com.condominios.api.ocorrencia.Ocorrencia;
import com.condominios.api.pagamento.Pagamento;
import com.condominios.api.reservaArea.ReservaArea;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/encomendas-pendentes")
    public List<Encomenda> encomendasPendentes() {
        return relatorioService.encomendasPendentes();
    }

    @GetMapping("/pagamentos-realizados")
    public List<Pagamento> pagamentosRealizados() {
        return relatorioService.pagamentosRealizados();
    }

    @GetMapping("/reservas-areas-comuns")
    public List<ReservaArea> reservasAreasComuns() {
        return relatorioService.reservasAreasComuns();
    }

    @GetMapping("/ocorrencias-registradas")
    public List<Ocorrencia> ocorrenciasRegistradas() {
        return relatorioService.ocorrenciasRegistradas();
    }
}
