package com.controleDeAcesso.controller;

import com.controleDeAcesso.dto.AcessoDTO;
import com.controleDeAcesso.service.AcessoService;
import com.controleDeAcesso.view.AcessoView;
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

    @GetMapping(value = "/acessos")
    public String listarAcessos(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(required = false) String pesquisa,
                                Model model){

        model.addAttribute("botaoPressionado","acessos");

        //vão se tornar uma linha só futuramente com JPA
        PageRequest pageable = PageRequest.of(page,Constantes.registrosPorPagina);
        Page<AcessoView> pagina =  PageUtils.toPage(acessoService.consultarAcessosViewOrderData(),pageable);
        //Page<AcessoDTO> pagina = acessoService.buscarAcessosPaginados(page, tamanhoPagina, pesquisa);

        model.addAttribute("acessosView",pagina.getContent());
        model.addAttribute("paginaAtual", page);
        model.addAttribute("totalPaginas", pagina.getTotalPages());
        model.addAttribute("pesquisa", pesquisa);

        return "acessos";
    }
}
