package com.controleDeAcesso.model;

public class Autorizacao {
    private int id;
    private int pesId;
    private int locId;

    public Autorizacao(int pesId, int locId) {
        this.pesId = pesId;
        this.locId = locId;
    }

    public Autorizacao(int id,int pesId, int locId ){
        this(pesId,locId);
        this.id = id;
    }

    public Autorizacao() {
    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

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
        return "Autorizacao{" +
                "id=" + id +
                ", pesId=" + pesId +
                ", locId=" + locId +
                '}';
    }
}