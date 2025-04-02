package com.platzi.market.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludar")
public class ControllerHolaMundo {

    @RequestMapping("/hola")
    public String saludar(){
        return "Tengo ganas de empezar a trabajar";
    }
}
