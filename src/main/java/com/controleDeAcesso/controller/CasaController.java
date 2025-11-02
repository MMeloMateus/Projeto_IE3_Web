package com.controleDeAcesso.controller;

import com.controleDeAcesso.service.CasaService;
import com.controleDeAcesso.view.CasaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;


import java.util.List;

@Controller
public class CasaController {

    @Autowired
    private CasaService casaService;

    public CasaController(){
        casaService = new CasaService();
    }

    @GetMapping(value = "/casas")
    public String listarCasas(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(required = false) String pesquisa,
                                Model model){

        model.addAttribute("botaoPressionado","casas");

        //vão se tornar uma linha só futuramente com JPA
        PageRequest pageable = PageRequest.of(page,Constantes.registrosPorPagina);
        Page<CasaView> pagina =  PageUtils.toPage(casaService.consultarCasasView(),pageable);
        //Page<CasaView> pagina = acessoService.buscarAcessosPaginados(page, tamanhoPagina, pesquisa);

        model.addAttribute("casasView",pagina.getContent());
        model.addAttribute("paginaAtual", page);
        model.addAttribute("totalPaginas", pagina.getTotalPages());
        model.addAttribute("pesquisa", pesquisa);

        return "casas";
    }
}
