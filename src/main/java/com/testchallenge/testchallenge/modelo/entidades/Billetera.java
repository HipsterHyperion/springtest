package com.testchallenge.testchallenge.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="billeteras")
public class Billetera implements Serializable {

    //_##_Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nombre;
    @Column(name="saldo_total")
    private BigDecimal saldoTotal;

    @Column(name="fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name="fecha_mod")
    private LocalDateTime fechaMod;

    @OneToMany(
            mappedBy = "billetera",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JsonIgnoreProperties({"billetera"})
    private Set<Saldo> saldos;

    @OneToMany(
            mappedBy = "billetera",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JsonIgnoreProperties({"billetera"})
    private Set<Registro> registros;


    @ManyToOne(
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "billeteras"})
    private Usuario usuario;


    //_##_Constructor
    public Billetera() {
        Saldo saldoARS = new Saldo();
        saldoARS.setSaldo(new BigDecimal(0));
        saldos = new HashSet<>();
        saldos.add(saldoARS);
        saldoTotal=new BigDecimal(0);
    }

    public Billetera(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public BigDecimal getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(BigDecimal saldoTotal) {
        saldoTotal = saldoTotal;
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

    public Set<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(Set<Registro> registros) {
        this.registros = registros;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        return "Billetera{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", SaldoTotal=" + saldoTotal +
                ", fechaAlta=" + fechaAlta +
                ", fechaMod=" + fechaMod +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Billetera billetera = (Billetera) o;
        return id.equals(billetera.id) && nombre.equals(billetera.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    /*
    CONSULTA DE SALDO DE UNA BILLETERA
     */
    public BigDecimal calcularSaldoTotal(){
        BigDecimal i = new BigDecimal(0);
        for (Saldo s : saldos) {
            i.add(s.saldoPeso());
        }
        saldoTotal = i;
        return saldoTotal;
    }

    public void hacerDeposito(Divisa divisa){
        for (Saldo s:saldos) {
            s.getDivisa().equals(divisa);

        }
    }
}
