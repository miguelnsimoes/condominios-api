package com.condominios.api.relatorio;

import com.condominios.api.encomenda.Encomenda;
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
}
