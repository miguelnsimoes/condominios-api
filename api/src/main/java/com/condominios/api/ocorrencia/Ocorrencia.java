package com.condominios.api.ocorrencia;

import com.condominios.api.morador.Morador;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ocorrencia")
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(name = "data_registro", nullable = false)
    private LocalDateTime dataRegistro;

    @Column(nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "morador_id", nullable = false)
    private Morador morador;

    public Ocorrencia() {
        this.dataRegistro = LocalDateTime.now();
        this.status = "ABERTA";
    }

    public Ocorrencia(String titulo, String descricao, Morador morador) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.morador = morador;
        this.dataRegistro = LocalDateTime.now();
        this.status = "ABERTA";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDateTime getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDateTime dataRegistro) { this.dataRegistro = dataRegistro; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Morador getMorador() { return morador; }
    public void setMorador(Morador morador) { this.morador = morador; }
}