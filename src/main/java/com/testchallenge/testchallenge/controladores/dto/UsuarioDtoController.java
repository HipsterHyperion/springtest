package com.testchallenge.testchallenge.controladores.dto;

import com.testchallenge.testchallenge.servicios.contratos.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/usaurios")
//@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class UsuarioDtoController {

    //@Autowired
    private UsuarioDAO usuarioDAO;
}
