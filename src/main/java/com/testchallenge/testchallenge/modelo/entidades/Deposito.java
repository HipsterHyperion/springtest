package com.testchallenge.testchallenge.modelo.entidades;

import com.testchallenge.testchallenge.modelo.entidades.enumerado.Operacion;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "registro_id")
public class Deposito extends  Registro{

    //_##_Atributos
    private String moneda;
    private String monto;

    //_##_Constructor
    public Deposito() {
    }

    public Deposito(Integer id, Operacion tipoOperacion, String billeteraDestino, String moneda, String monto) {
        super(id, tipoOperacion, billeteraDestino);
        this.moneda = moneda;
        this.monto = monto;
    }

    //_##_Getter-Setter
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    //_##_Metodos
    @Override
    public String toString() {
        return super.toString() +
                "\tDeposito{" +
                "moneda='" + moneda + '\'' +
                ", monto='" + monto + '\'' +
                '}';
    }
}
