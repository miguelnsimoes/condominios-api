package com.condominios.api.pagamento;

import com.condominios.api.morador.Morador;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = true)
    private LocalDate dataPagamento;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "morador_id")
    private Morador morador;

    public Pagamento(){
        this.status = "PENDENTE";
    }

    public Pagamento(BigDecimal valor, String status, Morador morador, Long id, LocalDate dataPagamento) {
        this.valor = valor;
        this.status = status;
        this.morador = morador;
        this.id = id;
        this.dataPagamento = dataPagamento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
