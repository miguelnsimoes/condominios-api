package com.condominios.api.encomenda;

import com.condominios.api.apartamento.Apartamento;
import com.condominios.api.funcionario.Funcionario;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "encomenda")
public class Encomenda {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String descricao;

   @Column(nullable = false)
   private LocalDateTime chegada;

   @Column(nullable = false)
   private String status = "PENDENTE";
   private LocalDateTime dataRetirada;

   @ManyToOne
   @JoinColumn(name = "apartamento_id")
   private Apartamento apartamento;

   @ManyToOne
   @JoinColumn(name = "funcionario_id")
   private Funcionario funcionario;

   public Encomenda(){

   }

   public Encomenda(LocalDateTime chegada, LocalDateTime dataRetirada, String descricao, Long id, Funcionario funcionario, String status) {
      this.chegada = chegada;
      this.dataRetirada = dataRetirada;
      this.descricao = descricao;
      this.id = id;
      this.funcionario = funcionario;
      this.status = status;
   }

   public Apartamento getApartamento() {
      return apartamento;
   }

   public void setApartamento(Apartamento apartamento) {
      this.apartamento = apartamento;
   }

   public LocalDateTime getChegada() {
      return chegada;
   }

   public void setChegada(LocalDateTime chegada) {
      this.chegada = chegada;
   }

   public LocalDateTime getDataRetirada() {
      return dataRetirada;
   }

   public void setDataRetirada(LocalDateTime dataRetirada) {
      this.dataRetirada = dataRetirada;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Funcionario getFuncionario() {
      return funcionario;
   }

   public void setFuncionario(Funcionario funcionario) {
      this.funcionario = funcionario;
   }

   public String getDescricao() {
      return descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}
