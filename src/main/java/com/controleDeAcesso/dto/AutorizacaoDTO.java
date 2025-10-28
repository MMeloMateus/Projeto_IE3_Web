package com.controleDeAcesso.dto;

public class AutorizacaoDTO {
    private int id;
    private int pesId;
    private int locId;

    public AutorizacaoDTO(int pesId, int locId) {
        this.pesId = pesId;
        this.locId = locId;
    }

    public AutorizacaoDTO(int id,int pesId, int locId){
        this(pesId,locId);
        this.id = id;
    }

    public AutorizacaoDTO() {
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

    public int getLocId() {
        return locId;
    }

    public void setLocId(int locId) {
        this.locId = locId;
    }

    @Override
    public String toString() {
        return "AutorizacaoDTO{" +
                "id=" + id +
                ", pesId=" + pesId +
                ", locId=" + locId +
                '}';
    }
}