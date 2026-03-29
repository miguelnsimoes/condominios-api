package com.condominios.api.pagamento;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public List<Pagamento> getAll(){
        return pagamentoService.getAll();
    }

    @GetMapping("/{id}")
    public Pagamento getById(@PathVariable Long id) {
        return pagamentoService.findById(id);
    }

    @PostMapping
    public Pagamento create(@RequestBody Pagamento pagamento){
        return pagamentoService.save(pagamento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        pagamentoService.delete(id);
    }

    @PutMapping("/{id}/pagar")
    public Pagamento registrarPagamento(@PathVariable Long id){
        return pagamentoService.registrarPagamento(id);
    }

}
