package com.controleDeAcesso.controller;

import com.controleDeAcesso.dto.AcessoDTO;
import com.controleDeAcesso.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;


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

        int registrosPorPagina = 5;

        //vão se tornar uma linha só futuramente com JPA
        PageRequest pageable = PageRequest.of(page,registrosPorPagina);
        Page<AcessoDTO> pagina =  PageUtils.toPage(acessoService.consultarAcessosOrderData(),pageable);
        //Page<AcessoDTO> pagina = acessoService.buscarAcessosPaginados(page, tamanhoPagina, pesquisa);

        model.addAttribute("acessosDTO",pagina.getContent());
        model.addAttribute("paginaAtual", page);
        model.addAttribute("totalPaginas", pagina.getTotalPages());
        model.addAttribute("pesquisa", pesquisa);

        return "acessos";
    }
}
