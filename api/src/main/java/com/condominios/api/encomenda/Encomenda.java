package com.condominios.api.encomenda;

import com.condominios.api.apartamento.Apartamento;
import com.condominios.api.funcionario.Funcionario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "encomenda")
public class Encomenda {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String descricao;

   @Column(nullable = false)
   private LocalDateTime chegada;

   @Column(nullable = true)
   private LocalDateTime dataRetirada;

   @Column(nullable = false)
   private String status;

   @ManyToOne
   @JoinColumn(name = "apartamento_id")
   private Apartamento apartamento;

   @ManyToOne
   @JoinColumn(name = "funcionario_id")
   private Funcionario funcionario;

   public Encomenda() {
      this.chegada = LocalDateTime.now();
      this.status = "PENDENTE";
   }

   public Encomenda(String descricao, Apartamento apartamento, Funcionario funcionario, LocalDateTime chegada, LocalDateTime dataRetirada, String status) {
         this.descricao = descricao;
         this.apartamento = apartamento;
         this.funcionario = funcionario;
         this.chegada = (chegada != null) ? chegada : LocalDateTime.now();
         this.dataRetirada = dataRetirada;
         this.status = (status != null) ? status : "PENDENTE";
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getDescricao() {
      return descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
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

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public Apartamento getApartamento() {
      return apartamento;
   }

   public void setApartamento(Apartamento apartamento) {
      this.apartamento = apartamento;
   }

   public Funcionario getFuncionario() {
      return funcionario;
   }

   public void setFuncionario(Funcionario funcionario) {
      this.funcionario = funcionario;
   }
}