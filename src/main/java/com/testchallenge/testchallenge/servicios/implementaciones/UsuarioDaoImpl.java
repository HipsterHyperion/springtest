package com.testchallenge.testchallenge.servicios.implementaciones;

import com.testchallenge.testchallenge.modelo.entidades.Usuario;
import com.testchallenge.testchallenge.repositorios.UsuarioRepositorio;
import com.testchallenge.testchallenge.servicios.contratos.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioDaoImpl extends GenericoDaoImpl<Usuario, UsuarioRepositorio> implements UsuarioDAO {

    @Autowired
    public UsuarioDaoImpl(UsuarioRepositorio repositorio) {
        super(repositorio);
    }
}
