package com.controleDeAcesso.controller;


import com.controleDeAcesso.service.VeiculoService;
import com.controleDeAcesso.view.VeiculoView;
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
    public String listarVeiculos(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(required = false) String pesquisa,
                                Model model){

        model.addAttribute("botaoPressionado","veiculos");

        //vão se tornar uma linha só futuramente com JPA
        PageRequest pageable = PageRequest.of(page,Constantes.registrosPorPagina);
        Page<VeiculoView> pagina =  PageUtils.toPage(veiculoService.consultarVeiculosViewGeral(),pageable);
        //Page<VeiculoView> pagina = veiculoService.buscarAcessosPaginados(page, tamanhoPagina, pesquisa);

        model.addAttribute("veiculosView",pagina.getContent());
        model.addAttribute("paginaAtual", page);
        model.addAttribute("totalPaginas", pagina.getTotalPages());
        model.addAttribute("pesquisa", pesquisa);

        return "veiculos";
    }
}
