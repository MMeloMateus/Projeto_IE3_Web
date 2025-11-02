package com.controleDeAcesso.controller;

import com.controleDeAcesso.dto.VeiculoDTO;
import com.controleDeAcesso.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

@Controller
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    public VeiculoController(){
        veiculoService = new VeiculoService();
    }

    @GetMapping(value = "/veiculos")
    public String listarAcessos(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(required = false) String pesquisa,
                                Model model){

        model.addAttribute("botaoPressionado","veiculos");

        int registrosPorPagina = 5;

        //vão se tornar uma linha só futuramente com JPA
        PageRequest pageable = PageRequest.of(page,registrosPorPagina);
        Page<VeiculoDTO> pagina =  PageUtils.toPage(veiculoService.consultarVeiculosGeral(),pageable);
        //Page<VeiculoDTO> pagina = veiculoService.buscarAcessosPaginados(page, tamanhoPagina, pesquisa);

        model.addAttribute("veiculosDTO",pagina.getContent());
        model.addAttribute("paginaAtual", page);
        model.addAttribute("totalPaginas", pagina.getTotalPages());
        model.addAttribute("pesquisa", pesquisa);

        return "veiculos";
    }
}
