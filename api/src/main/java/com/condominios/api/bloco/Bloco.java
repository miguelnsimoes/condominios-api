package com.condominios.api.bloco;
import com.condominios.api.apartamento.Apartamento;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bloco")
public class Bloco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "bloco")
    private List<Apartamento> apartamento;

    public Bloco(){

    }

    public Bloco(List<Apartamento> apartamento, String nome, Long id) {
        this.apartamento = apartamento;
        this.nome = nome;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome; 
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Apartamento> getApartamento() {
        return apartamento;
    }

    public void setApartamento(List<Apartamento> apartamento) {
        this.apartamento = apartamento;
    }
}
