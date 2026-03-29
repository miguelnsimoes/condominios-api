package com.condominios.api.pagamento;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public List<Pagamento> getAll(){
        return pagamentoRepository.findAll();
    }

    public Pagamento findById(Long id){
        return pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento nao encontrado"));
    }

    public Pagamento save(Pagamento pagamento){
        return pagamentoRepository.save(pagamento);
    }

    public void delete(Long id){
        pagamentoRepository.deleteById(id);
    }

    public Pagamento registrarPagamento(Long id){
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("pagamento não encontrado"));

        pagamento.setDataPagamento(LocalDate.now());
        return pagamentoRepository.save(pagamento);
    }
}
