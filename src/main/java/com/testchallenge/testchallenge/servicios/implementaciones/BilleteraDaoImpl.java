package com.testchallenge.testchallenge.servicios.implementaciones;

import com.testchallenge.testchallenge.modelo.entidades.Billetera;
import com.testchallenge.testchallenge.repositorios.BilleteraRepositorio;
import com.testchallenge.testchallenge.servicios.contratos.BilleteraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BilleteraDaoImpl extends GenericoDaoImpl<Billetera, BilleteraRepositorio> implements BilleteraDAO {

    @Autowired
    public BilleteraDaoImpl(BilleteraRepositorio repositorio) {
        super(repositorio);
    }
}
