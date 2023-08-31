package com.testchallenge.testchallenge.modelo.dto;

import com.testchallenge.testchallenge.modelo.entidades.enumerado.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer id;
    private String dni;
    private String nombre;
    private String apellido;
    private Sexo sexo;

}
