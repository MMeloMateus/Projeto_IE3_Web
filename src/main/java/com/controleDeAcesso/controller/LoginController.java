package com.controleDeAcesso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String exibirLogin(){
        return "login";
    }

    @PostMapping(value = "/login")
    public String processarLogin(@RequestParam String login,
                                @RequestParam String senha,
                                Model model){


        model.addAttribute("login",login);
        model.addAttribute("senha",senha);

        if(login != null && senha != null){
            if(login.equals("admin") && senha.equals("admin")){
                return "redirect:/home";
            }
            else {
                System.out.println("login ou senha incorreto(s)");
            }
        }

        System.out.println("Preencha os campos");
        return "login";

    }
}
