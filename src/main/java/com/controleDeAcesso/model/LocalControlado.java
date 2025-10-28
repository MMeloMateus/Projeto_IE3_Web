package com.controleDeAcesso.model;

public class LocalControlado {

    private int id;
    private String nome;
    private String descricao;

    public LocalControlado(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public LocalControlado(int id, String nome, String descricao) {
        this(nome,descricao);
        this.id = id;
    }

    public LocalControlado(){}

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
        return "LocalControlado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}

