package com.testchallenge.testchallenge.repositorios;

import com.testchallenge.testchallenge.modelo.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {


}
