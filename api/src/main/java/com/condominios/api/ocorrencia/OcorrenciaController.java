package com.condominios.api.ocorrencia;

import com.condominios.api.infra.exception.BusinessException;
import com.condominios.api.infra.security.SecurityUtils;
import com.condominios.api.morador.Morador;
import com.condominios.api.morador.MoradorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;
    private final SecurityUtils securityUtils;
    private final MoradorRepository moradorRepository;

    public OcorrenciaController(
            OcorrenciaService ocorrenciaService,
            SecurityUtils securityUtils,
            MoradorRepository moradorRepository) {
        this.ocorrenciaService = ocorrenciaService;
        this.securityUtils = securityUtils;
        this.moradorRepository = moradorRepository;
    }

    @GetMapping
    public List<Ocorrencia> getAll() {
        if (securityUtils.isMorador()) {
            Long ownId = securityUtils.getMoradorIdLogado();
            if (ownId == null) return Collections.emptyList();
            return ocorrenciaService.findByMoradorId(ownId);
        }
        return ocorrenciaService.getAll();
    }

    @GetMapping("/{id}")
    public Ocorrencia getById(@PathVariable Long id) {
        Ocorrencia ocorrencia = ocorrenciaService.findById(id);
        if (securityUtils.isMorador() && ocorrencia.getMorador() != null) {
            securityUtils.validarAcessoMorador(ocorrencia.getMorador().getId());
        }
        return ocorrencia;
    }

    @PostMapping
    public Ocorrencia create(@RequestBody Ocorrencia ocorrencia) {
        if (securityUtils.isMorador()) {
            Long ownId = securityUtils.getMoradorIdLogado();
            if (ownId == null) {
                throw new BusinessException("Conta não vinculada a um morador. Informe seu CPF no cadastro.");
            }
            Morador morador = moradorRepository.findById(ownId)
                    .orElseThrow(() -> new BusinessException("Morador não encontrado"));
            ocorrencia.setMorador(morador);
        } else if (ocorrencia.getMorador() == null || ocorrencia.getMorador().getId() == null) {
            throw new BusinessException("Morador é obrigatório para registrar a ocorrência");
        }
        return ocorrenciaService.save(ocorrencia);
    }

    @PutMapping("/{id}")
    public Ocorrencia update(@PathVariable Long id, @RequestBody Ocorrencia ocorrencia) {
        if (securityUtils.isMorador()) {
            throw new BusinessException("Moradores não podem alterar ocorrências");
        }
        return ocorrenciaService.update(id, ocorrencia);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ocorrenciaService.delete(id);
    }
}
