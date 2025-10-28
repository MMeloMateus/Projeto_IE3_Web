package com.controleDeAcesso.dto;

public class LocalControladoDTO {

    private int id;
    private String nome;
    private String descricao;

    public LocalControladoDTO(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public LocalControladoDTO(int id, String nome, String descricao) {
        this(nome,descricao);
        this.id = id;
    }

    public LocalControladoDTO(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "LocalControladoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}

