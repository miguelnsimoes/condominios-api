package com.condominios.api.pagamento;

import com.condominios.api.morador.Morador;
import com.condominios.api.morador.MoradorRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//essa classe gera cobranças automaticamente
@Component
public class PagamentoScheduler {

    private final MoradorRepository moradorRepository;
    private final PagamentoRepository pagamentoRepository;

    public PagamentoScheduler(MoradorRepository moradorRepository, PagamentoRepository pagamentoRepository) {
        this.moradorRepository = moradorRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    @Scheduled(cron = "0 0 0 1 * ?") // todo dia 1 do mes
    public void gerarPagamentosMensais() {

        List<Morador> moradores = moradorRepository.findAll();

        String referencia = LocalDate.now().getYear() + "-" + String.format("%02d", LocalDate.now().getMonthValue());

        for (Morador morador : moradores) {
            boolean jaExiste = pagamentoRepository.existsByMoradorAndReferencia(morador, referencia); //metodo la no repository

            if (!jaExiste) {

                Pagamento pagamento = new Pagamento();
                pagamento.setMorador(morador);
                pagamento.setValor(new BigDecimal("1500.00"));

                pagamentoRepository.save(pagamento);
            }
        }
    }
}