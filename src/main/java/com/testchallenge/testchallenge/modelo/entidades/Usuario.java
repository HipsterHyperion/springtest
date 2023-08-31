package com.testchallenge.testchallenge.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testchallenge.testchallenge.modelo.entidades.enumerado.Sexo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {

    //_##_Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 10)
    @NotNull
    @NotEmpty
    @Size(min = 7, max = 10)
    private String dni;
    @Column(nullable = false, length = 80)
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 80)
    private String nombre;
    @Column(nullable = false, length = 80)
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 80)
    private String apellido;
    @Column(unique = true, nullable = false)
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @Column(unique = true, nullable = false, length = 15)
    @NotNull
    @NotEmpty
    @Size(min = 10, max = 15)
    private String telefono;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(name="fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name="fecha_mod")
    private LocalDateTime fechaMod;

    @OneToMany(
            mappedBy = "usuario",
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties({"usuario"})
    private Set<Billetera> billeteras;


    //_##_Constructor
    public Usuario() {
    }

    public Usuario(Integer id, String dni, String nombre, String apellido, String email, String telefono, Sexo sexo) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.sexo = sexo;
    }

    //_##_Getter-Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
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

    //_##_Metodos
    @PrePersist
    private void preAlta(){
        this.fechaAlta=LocalDateTime.now();
    }

    @PreUpdate
    private void preMod(){
        this.fechaMod=LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", sexo=" + sexo +
                ", fechaAlta=" + fechaAlta +
                ", fechaMod=" + fechaMod +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id) && dni.equals(usuario.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni);
    }

    /*
    CONSULTA EL SALDO TOTAL DE TODAS LAS BILLETERAS DE UN USUARIO
     */
    public BigDecimal calcularSaldototal(){
        BigDecimal i = new BigDecimal(0);
        for (Billetera b: billeteras) {
            i.add(b.calcularSaldoTotal());
        }
        return i;
    }
}
