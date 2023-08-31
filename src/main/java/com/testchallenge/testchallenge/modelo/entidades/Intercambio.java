package com.testchallenge.testchallenge.modelo.entidades;

import com.testchallenge.testchallenge.modelo.entidades.enumerado.Operacion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "registro_id")
public class Intercambio extends Registro{

    //_##_Atributos
    @Column(name = "billetera_id_origen")
    private String billeteraOrigen;
    @Column(name = "moneda_origen")
    private String monedaOrigen;
    @Column(name = "monto_origen")
    private String montoOrigen;
    @Column(name = "moneda_destino")
    private String monedaDestino;
    @Column(name = "monto_destino")
    private String montoDestino;

    //_##_Constructor
    public Intercambio() {
    }

    public Intercambio(Integer id, Operacion tipoOperacion, String billeteraDestino, String billeteraOrigen, String monedaOrigen, String montoOrigen, String monedaDestino, String montoDestino) {
        super(id, tipoOperacion, billeteraDestino);
        this.billeteraOrigen = billeteraOrigen;
        this.monedaOrigen = monedaOrigen;
        this.montoOrigen = montoOrigen;
        this.monedaDestino = monedaDestino;
        this.montoDestino = montoDestino;
    }

    //_##_Getter-Setter
    public String getBilleteraOrigen() {
        return billeteraOrigen;
    }

    public void setBilleteraOrigen(String billeteraOrigen) {
        this.billeteraOrigen = billeteraOrigen;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public String getMontoOrigen() {
        return montoOrigen;
    }

    public void setMontoOrigen(String montoOrigen) {
        this.montoOrigen = montoOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }

    public String getMontoDestino() {
        return montoDestino;
    }

    public void setMontoDestino(String montoDestino) {
        this.montoDestino = montoDestino;
    }

    //_##_Metodos
    @Override
    public String toString() {
        return super.toString()+
                "\tIntercambio{" +
                "billeteraDestino='" + billeteraOrigen + '\'' +
                ", monedaOrigen='" + monedaOrigen + '\'' +
                ", montoOrigen='" + montoOrigen + '\'' +
                ", monedaDestino='" + monedaDestino + '\'' +
                ", montoDestino='" + montoDestino + '\'' +
                '}';
    }
}
