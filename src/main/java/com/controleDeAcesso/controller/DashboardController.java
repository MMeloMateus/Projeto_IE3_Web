package com.controleDeAcesso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping(value = "/dashboard")
    public String exibirLogin(){
        return "dashboard";
    }
}
