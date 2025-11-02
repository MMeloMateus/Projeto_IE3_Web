package com.controleDeAcesso.Views;

import com.controleDeAcesso.model.Veiculo;

public class VeiculoView extends Veiculo {
    private String pesNome;

    public VeiculoView(String placa, int pesId, String cor, String modelo, String pesNome) {
        super(placa, pesId, cor, modelo);
        this.pesNome = pesNome;
    }

    public VeiculoView(String pesNome) {
        this.pesNome = pesNome;
    }

    public VeiculoView() {
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }
}
