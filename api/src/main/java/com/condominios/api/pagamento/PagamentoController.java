package com.condominios.api.pagamento;

import com.condominios.api.infra.exception.BusinessException;
import com.condominios.api.infra.security.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final SecurityUtils securityUtils;

    public PagamentoController(PagamentoService pagamentoService, SecurityUtils securityUtils) {
        this.pagamentoService = pagamentoService;
        this.securityUtils = securityUtils;
    }

    @GetMapping
    public List<Pagamento> getAll(@RequestParam(required = false) Long moradorId){
        if (securityUtils.isMorador()) {
            Long ownId = securityUtils.getMoradorIdLogado();
            if (ownId == null) return Collections.emptyList();
            return pagamentoService.findByMoradorId(ownId);
        }
        if (moradorId != null) {
            return pagamentoService.findByMoradorId(moradorId);
        }
        return pagamentoService.getAll();
    }

    @GetMapping("/{id}")
    public Pagamento getById(@PathVariable Long id) {
        Pagamento pagamento = pagamentoService.findById(id);
        if (securityUtils.isMorador() && pagamento.getMorador() != null) {
            securityUtils.validarAcessoMorador(pagamento.getMorador().getId());
        }
        return pagamento;
    }

    @PostMapping
    public Pagamento create(@RequestBody Pagamento pagamento){
        if (!securityUtils.isAdm()) {
            throw new BusinessException("Apenas administradores podem gerar pagamentos");
        }
        return pagamentoService.save(pagamento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        if (!securityUtils.isAdm()) {
            throw new BusinessException("Apenas administradores podem excluir pagamentos");
        }
        pagamentoService.delete(id);
    }

    @PutMapping("/{id}/pagar")
    public Pagamento registrarPagamento(@PathVariable Long id){
        if (!securityUtils.isAdm()) {
            throw new BusinessException("Apenas administradores podem confirmar pagamentos");
        }
        return pagamentoService.registrarPagamento(id);
    }
}
