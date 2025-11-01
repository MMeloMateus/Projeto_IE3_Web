package com.controleDeAcesso.controller;

import com.controleDeAcesso.dto.AcessoDTO;
import com.controleDeAcesso.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    public AcessoController(){
        acessoService = new AcessoService();
    }

    public void init(){
        System.out.println("Teste Acessos");
    }

    @GetMapping(value = "/acessos")
    public String listarAcessos(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(required = false) String pesquisa,
                                Model model){

        User user = new User();

        if (user == null) {
            model.addAttribute("user", new User());
        }
        else {
            model.addAttribute("user", user);
        }

        int tamanhoPagina = 5;

        model.addAttribute("paginaAtual", 1);
        model.addAttribute("totalPaginas", 5);
        model.addAttribute("pesquisa", pesquisa);
        //user.setPesquisa("teste");

        //List<AcessoDTO> lista = acessoService.consultarAcessosOrderData();
        //model.addAttribute("acessos",acessoService.consultarAcessosOrderData());
        //model.addAttribute("acessos",lista);
        return "acessos";
    }



}
