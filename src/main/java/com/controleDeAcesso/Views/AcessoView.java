package com.controleDeAcesso.Views;

import com.controleDeAcesso.util.StatusAcesso;
import com.controleDeAcesso.util.TipoAcesso;

import java.sql.Timestamp;

public class AcessoView {
    private int id;
    private int pesId;
    private String pesNome;
    private int locId;
    private String locNome;
    private Timestamp data;
    private String tipoPessoa;
    private TipoAcesso tipoAcesso;
    private StatusAcesso statusAcesso;

    public AcessoView(int id, int pesId, String pesNome, int locId, String locNome, Timestamp data, String tipoPessoa, TipoAcesso tipoAcesso, StatusAcesso statusAcesso) {
        this.id = id;
        this.pesId = pesId;
        this.pesNome = pesNome;
        this.locId = locId;
        this.locNome = locNome;
        this.data = data;
        this.tipoPessoa = tipoPessoa;
        this.tipoAcesso = tipoAcesso;
        this.statusAcesso = statusAcesso;
    }

    public AcessoView() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPesId() {
        return pesId;
    }

    public void setPesId(int pesId) {
        this.pesId = pesId;
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    public int getLocId() {
        return locId;
    }

    public void setLocId(int locId) {
        this.locId = locId;
    }

    public String getLocNome() {
        return locNome;
    }

    public void setLocNome(String locNome) {
        this.locNome = locNome;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public StatusAcesso getStatusAcesso() {
        return statusAcesso;
    }

    public void setStatusAcesso(StatusAcesso statusAcesso) {
        this.statusAcesso = statusAcesso;
    }
}
