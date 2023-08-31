package com.testchallenge.testchallenge.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="divisas")
public class Divisa implements Serializable {

    //_##_Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String nombre;
    @NotNull
    @NotEmpty
    @Positive
    private BigDecimal valor;
    @Column(name="fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name="fecha_mod")
    private LocalDateTime fechaMod;

    @OneToMany(
            mappedBy = "divisa",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JsonIgnoreProperties({"divisa"})
    private Set<Saldo> saldos;


    //_##_Constructor
    public Divisa() {
    }

    public Divisa(Integer id, String nombre, BigDecimal valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    //_##_Getter-Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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

    public Set<Saldo> getSaldos() {
        return saldos;
    }

    public void setSaldos(Set<Saldo> saldos) {
        this.saldos = saldos;
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
        return "Divisa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", fechaAlta=" + fechaAlta +
                ", fechaMod=" + fechaMod +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Divisa divisa = (Divisa) o;
        return id.equals(divisa.id) && nombre.equals(divisa.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
