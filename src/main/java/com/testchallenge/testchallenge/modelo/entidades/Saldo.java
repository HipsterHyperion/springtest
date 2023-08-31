package com.testchallenge.testchallenge.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="saldos")
public class Saldo implements Serializable {

    //_##_Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @NotEmpty
    private BigDecimal saldo;

    @Column(name="fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name="fecha_mod")
    private LocalDateTime fechaMod;
    @ManyToOne(
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "divisa_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "saldos"})
    private Divisa divisa;
    @ManyToOne(
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "billetera_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "saldos"})
    private Billetera billetera;

    //_##_Constructor
    public Saldo() {
    }

    public Saldo(Integer id, BigDecimal saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    //_##_Getter-Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDateTime getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(LocalDateTime fechaMod) {
        this.fechaMod = fechaMod;
    }

    public Divisa getDivisa() {
        return divisa;
    }

    public void setDivisa(Divisa divisa) {
        this.divisa = divisa;
    }

    public Billetera getBilletera() {
        return billetera;
    }

    public void setBilletera(Billetera billetera) {
        this.billetera = billetera;
    }

    //_##_Metodos
    @PrePersist
    private void preAlta(){
        this.fechaAlta=LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        this.fechaMod=LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Saldo{" +
                "id=" + id +
                ", saldo=" + saldo +
                ", fechaAlta=" + fechaAlta +
                ", fechaMod=" + fechaMod +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Saldo saldo = (Saldo) o;
        return Objects.equals(id, saldo.id) && Objects.equals(fechaAlta, saldo.fechaAlta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaAlta);
    }

    public BigDecimal saldoPeso(){
        BigDecimal i = saldo.multiply(divisa.getValor());
        return i;
    }
}
