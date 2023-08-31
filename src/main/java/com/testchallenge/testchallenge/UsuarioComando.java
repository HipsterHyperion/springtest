package com.testchallenge.testchallenge;

import com.testchallenge.testchallenge.modelo.entidades.Usuario;
import com.testchallenge.testchallenge.modelo.entidades.enumerado.Sexo;
import com.testchallenge.testchallenge.servicios.contratos.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UsuarioComando implements CommandLineRunner {

    @Autowired
    private UsuarioDAO servicio;

    @Override
    public void run(String... args) throws Exception {

        //Usuario admin = new Usuario(null, "123123123", "unnombre", "unapellido", "unemail", "untelefono", Sexo.HOMBRE);
        /*
        Usuario usu1 = new Usuario(null, "88488333", "ana", "dais", "exampl1e1@example.com", "26571235432", Sexo.MUJER);
        Usuario usu2 = new Usuario(null, "34934945", "martin", "molina", "examplet2@example.com", "2665023443", Sexo.MUJER);
        Usuario usu3 = new Usuario(null, "46668889", "lucas", "alfredo", "examplee3@example.com", "2664662688", Sexo.HOMBRE);
        Usuario usu4 = new Usuario(null, "04132203", "manolo", "cococo", "exampl2e4@example.com", "2621235338", Sexo.OTRO);
        servicio.save(usu1);
        servicio.save(usu2);
        servicio.save(usu3);
        servicio.save(usu4);
            */



    }
}
