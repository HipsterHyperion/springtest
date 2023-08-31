package com.testchallenge.testchallenge.modelo.mapper;

import com.testchallenge.testchallenge.modelo.dto.UsuarioDTO;
import com.testchallenge.testchallenge.modelo.entidades.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO mapUsuario(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setDni(usuario.getDni());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setSexo(usuario.getSexo());
        return dto;
    }


}
