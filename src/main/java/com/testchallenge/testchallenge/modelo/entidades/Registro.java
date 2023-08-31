package com.testchallenge.testchallenge.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.testchallenge.testchallenge.modelo.entidades.enumerado.Operacion;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "registros")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Intercambio.class, name = "intercambio"),
        @JsonSubTypes.Type(value = Deposito.class, name = "deposito")
})
public class  Registro implements Serializable {

    //_##_Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "operacion")
    @Enumerated(EnumType.STRING)
    private Operacion tipoOperacion;
    @Column(name = "billetera_id_destino")
    private String billeteraDestino;

    @ManyToOne(
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "billetera_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "registros"})
    private Billetera billetera;

    //_##_Constructor
    public Registro() {
    }

    public Registro(Integer id, Operacion tipoOperacion, String billeteraDestino) {
        this.id = id;
        this.tipoOperacion = tipoOperacion;
        this.billeteraDestino = billeteraDestino;
    }

    //_##_Getter-Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Operacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(Operacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Billetera getBilletera() {
        return billetera;
    }

    public void setBilletera(Billetera billetera) {
        this.billetera = billetera;
    }

    public String getBilleteraDestino() {
        return billeteraDestino;
    }

    public void setBilleteraDestino(String billeteraDestino) {
        this.billeteraDestino = billeteraDestino;
    }

    //_##_Metodos
    @PrePersist
    private void preAlta(){
        this.fechaAlta=LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Registro{" +
                "id=" + id +
                ", fechaAlta=" + fechaAlta +
                ", tipoOperacion=" + tipoOperacion +
                ", billeteraDestino=" + billetera+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registro registro = (Registro) o;
        return Objects.equals(id, registro.id) && Objects.equals(fechaAlta, registro.fechaAlta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaAlta);
    }
}
