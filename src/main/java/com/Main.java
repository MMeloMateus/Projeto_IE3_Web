package com;

import com.controleDeAcesso.dao.*;
import com.controleDeAcesso.dto.AcessoDTO;
import com.controleDeAcesso.model.*;
import com.controleDeAcesso.service.AcessoService;

import java.sql.SQLException;
import java.util.Date;
import com.controleDeAcesso.util.TipoPessoa;

public class Main {

    public static void main(String[] args) {

        TipoPessoa p = TipoPessoa.MORADOR;

        System.out.println("1 "  + p);
        System.out.println("2 " + p.equals(TipoPessoa.MORADOR));
        System.out.println("3 " + p.equals("MORADOR"));
        System.out.println("4 " + p.compareTo(TipoPessoa.MORADOR));
        System.out.println("5 " + p.name());

    }
}