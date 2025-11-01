package com.controleDeAcesso;

import com.controleDeAcesso.service.AcessoService;

public class Main {

    public static void main(String[] args) {






        AcessoService acessoService= new AcessoService();
        System.out.println(acessoService.consultarAcessosOrderData());


    }
}