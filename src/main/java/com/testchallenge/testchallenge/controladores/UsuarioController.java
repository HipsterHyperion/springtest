package com.testchallenge.testchallenge.controladores;

import com.testchallenge.testchallenge.exception.BadRequestException;
import com.testchallenge.testchallenge.modelo.entidades.Usuario;
import com.testchallenge.testchallenge.servicios.contratos.UsuarioDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true") //havingValue = "false" cuando se usa DTO
public class UsuarioController extends GenericController<Usuario, UsuarioDAO> {

    @Autowired
    public UsuarioController(UsuarioDAO servicioDAO) {
        super(servicioDAO);
        nombreEntidad = "usuario";
    }

    /*
    @GetMapping
    public List<Usuario> obternerTodos(){
        List<Usuario> listaUsuario = (List<Usuario>) servicioDAO.findAll();
        if(listaUsuario.isEmpty()){
            throw new BadRequestException(String.format("No existen usuarios."));
        }
        return listaUsuario;
    }
     */

    @PutMapping("/{id}")
    public ResponseEntity<?> usuarioUpdate(@PathVariable Integer id, @Valid @RequestBody Usuario usuario, BindingResult result){
        Map<String, Object> validaciones = new HashMap<>();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(
                    error-> validaciones.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(validaciones);
        }

        Map<String, Object> mensaje = new HashMap<>();
        Usuario usuarioUpdate = null;
        Optional<Usuario> oUsuario = servicioDAO.findById(id);
        if(!oUsuario.isPresent()){
            //throw new BadRequestException(String.format("Usuario no existe."));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El %s con ID %d, no existe.", nombreEntidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        usuarioUpdate = oUsuario.get();
        usuarioUpdate.setNombre(usuario.getNombre());
        usuarioUpdate.setApellido(usuario.getApellido());
        usuarioUpdate.setSexo(usuario.getSexo());

        mensaje.put("datos", servicioDAO.save(usuarioUpdate));
        mensaje.put("success", Boolean.TRUE);

        return ResponseEntity.ok(mensaje);
    }
}
