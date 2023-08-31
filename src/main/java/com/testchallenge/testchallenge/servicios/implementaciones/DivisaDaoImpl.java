package com.testchallenge.testchallenge.servicios.implementaciones;

import com.testchallenge.testchallenge.modelo.entidades.Divisa;
import com.testchallenge.testchallenge.repositorios.DivisaRepositorio;
import com.testchallenge.testchallenge.servicios.contratos.DivisaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivisaDaoImpl extends GenericoDaoImpl<Divisa, DivisaRepositorio> implements DivisaDAO {

    @Autowired
    public DivisaDaoImpl(DivisaRepositorio repositorio) {
        super(repositorio);
    }
}
