package com.testchallenge.testchallenge.servicios.implementaciones;

import com.testchallenge.testchallenge.servicios.contratos.GenericoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public class GenericoDaoImpl<E, R extends CrudRepository<E, Integer>> implements GenericoDAO<E> {

    protected final R repositorio;

    public GenericoDaoImpl(R repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    @Transactional
    public E save(E entidad) {
        return repositorio.save(entidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return repositorio.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        repositorio.deleteById(id);
    }
}
