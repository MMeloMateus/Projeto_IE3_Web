package com.controleDeAcesso.view;

import com.controleDeAcesso.model.Casa;

public class CasaView {

    private int id;
    private String endereco;
    private String nomeMorador;
    private String tipoVinculo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeMorador() {
        return nomeMorador;
    }

    public void setNomeMorador(String nomeMorador) {
        this.nomeMorador = nomeMorador;
    }

    public String getTipoVinculo() {
        return tipoVinculo;
    }

    public void setTipoVinculo(String tipoVinculo) {
        this.tipoVinculo = tipoVinculo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "CasaView{" +
                "id=" + id +
                ", endereco='" + endereco + '\'' +
                ", nomeMorador='" + nomeMorador + '\'' +
                ", tipoVinculo='" + tipoVinculo + '\'' +
                '}' + '\n';
    }
}
