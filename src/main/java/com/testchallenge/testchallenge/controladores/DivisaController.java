package com.testchallenge.testchallenge.controladores;

import com.testchallenge.testchallenge.modelo.entidades.Divisa;
import com.testchallenge.testchallenge.servicios.contratos.DivisaDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/divisas")
public class DivisaController extends GenericController<Divisa, DivisaDAO>{

    @Autowired
    public DivisaController(DivisaDAO servicioDAO) {
        super(servicioDAO);
        nombreEntidad = "divisa";
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> divisaUpdate(@PathVariable Integer id, @Valid @RequestBody Divisa divisa, BindingResult result){
        Map<String, Object> validaciones = new HashMap<>();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(
                    error-> validaciones.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(validaciones);
        }

        Map<String, Object> mensaje = new HashMap<>();
        Divisa divisaUpdate = null;
        Optional<Divisa> oDivisa = servicioDAO.findById(id);
        if(!oDivisa.isPresent()){
            //throw new BadRequestException(String.format("No existe."));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("%s con ID %d, no existe.", nombreEntidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        divisaUpdate = oDivisa.get();
        divisaUpdate.setValor(divisa.getValor());

        mensaje.put("datos", servicioDAO.save(divisaUpdate));
        mensaje.put("success", Boolean.TRUE);

        return ResponseEntity.ok(mensaje);
    }
}
