package com.condominios.api.pagamento;

import com.condominios.api.morador.Morador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    boolean existsByMoradorAndReferencia(Morador morador, String referencia);

    List<Pagamento> findByDataPagamentoIsNotNull();

    void deleteByMorador_Id(Long moradorId);
}
