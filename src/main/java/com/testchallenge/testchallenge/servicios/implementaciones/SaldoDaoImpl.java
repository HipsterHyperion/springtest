package com.testchallenge.testchallenge.servicios.implementaciones;

import com.testchallenge.testchallenge.modelo.entidades.Saldo;
import com.testchallenge.testchallenge.repositorios.SaldoRepositorio;
import com.testchallenge.testchallenge.servicios.contratos.SaldoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaldoDaoImpl extends GenericoDaoImpl<Saldo, SaldoRepositorio> implements SaldoDAO {

    @Autowired
    public SaldoDaoImpl(SaldoRepositorio repositorio) {
        super(repositorio);
    }
}
