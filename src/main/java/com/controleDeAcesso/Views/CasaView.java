package com.controleDeAcesso.Views;

import com.controleDeAcesso.model.Casa;

public class CasaView  extends Casa {

    private String nomeMorador;
    private String tipoVinculo;

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
}
