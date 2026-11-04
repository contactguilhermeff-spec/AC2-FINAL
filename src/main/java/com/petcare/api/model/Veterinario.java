package com.petcare.api.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "veterinarios")
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "CRMV é obrigatório")
    @Column(nullable = false, unique = true)
    private String crmv;

    @ElementCollection
    @CollectionTable(name = "veterinario_especies", joinColumns = @JoinColumn(name = "veterinario_id"))
    @Column(name = "especie")
    private List<String> especiesAtendidas = new ArrayList<>();

    @OneToMany(mappedBy = "veterinario")
    private List<Consulta> consultas = new ArrayList<>();

    public Veterinario() {
    }

    public Veterinario(String nome, String crmv, List<String> especiesAtendidas) {
        this.nome = nome;
        this.crmv = crmv;
        this.especiesAtendidas = especiesAtendidas;
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

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public List<String> getEspeciesAtendidas() {
        return especiesAtendidas;
    }

    public void setEspeciesAtendidas(List<String> especiesAtendidas) {
        this.especiesAtendidas = especiesAtendidas;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
