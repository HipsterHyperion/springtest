package com.testchallenge.testchallenge.controladores;

import com.testchallenge.testchallenge.exception.BadRequestException;
import com.testchallenge.testchallenge.servicios.contratos.GenericoDAO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GenericController <E, S extends GenericoDAO<E>> {

    protected final S servicioDAO;
    protected String nombreEntidad;

    public GenericController(S servicioDAO) {
        this.servicioDAO = servicioDAO;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        Map<String, Object> mensaje = new HashMap<>();
        List<E> listado = (List<E>) servicioDAO.findAll();
        if (listado.isEmpty()) {
            //throw  new BadRequestException(String.format("No se han encontrado %ss", nombreEntidad));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se han encontrado %ss", nombreEntidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", listado);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable(required = false) Integer id) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<E> oEntidad = servicioDAO.findById(id);
        if (!oEntidad.isPresent()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El %s con ID %d, no existe.", nombreEntidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put(nombreEntidad, oEntidad.get());
        mensaje.put("success", Boolean.TRUE);

        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    public ResponseEntity<?> altaPersistir(@Valid @RequestBody E entidad){
        Map<String, Object> mensaje = new HashMap<>();
        mensaje.put(nombreEntidad, servicioDAO.save(entidad));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Integer id){
        Map<String, Object> mensaje = new HashMap<>();
        servicioDAO.deleteById(id);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }
}
