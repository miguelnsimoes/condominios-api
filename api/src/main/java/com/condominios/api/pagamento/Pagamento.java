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
    private LocalDate dataVencimento;

    @Column(nullable = false)
    private String referencia;

    @ManyToOne
    @JoinColumn(name = "morador_id")
    private Morador morador;

    public Pagamento() {

    }
    //pra referenciar o mes de pagamento
    @PrePersist
    public void prePersist(){
        if(this.dataVencimento == null){
            this.dataVencimento = LocalDate.now().plusDays(30);
        }

        if(this.referencia == null){
            this.referencia = LocalDate.now().getYear() + "-" + String.format("%02d", LocalDate.now().getMonthValue());
        }
    }

    public Pagamento(BigDecimal valor, Morador morador, LocalDate dataPagamento, LocalDate dataVencimento) {
        this.valor = valor;
        this.morador = morador;
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }

    public StatusPagamento getStatus() {
        if (this.dataPagamento != null) {
            return StatusPagamento.PAGO;
        }

        if (LocalDate.now().isAfter(this.dataVencimento)) {
            return StatusPagamento.ATRASADO;
        }

        return StatusPagamento.PENDENTE;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }
}