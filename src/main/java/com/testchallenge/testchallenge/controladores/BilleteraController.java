package com.testchallenge.testchallenge.controladores;

import com.testchallenge.testchallenge.modelo.entidades.Billetera;
import com.testchallenge.testchallenge.modelo.entidades.Usuario;
import com.testchallenge.testchallenge.servicios.contratos.BilleteraDAO;
import com.testchallenge.testchallenge.servicios.contratos.UsuarioDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/billeteras")
public class BilleteraController extends GenericController<Billetera, BilleteraDAO>{

    private final UsuarioDAO usuarioDAO;

    @Autowired
    public BilleteraController(BilleteraDAO servicioDAO, UsuarioDAO usuarioDAO) {
        super(servicioDAO);
        this.usuarioDAO = usuarioDAO;
        nombreEntidad = "billetera";
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> billeteraUpdate(@PathVariable Integer id, @Valid @RequestBody Billetera billetera, BindingResult result){
        Map<String, Object> validaciones = new HashMap<>();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(
                    error-> validaciones.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(validaciones);
        }
        Map<String, Object> mensaje = new HashMap<>();
        Billetera billeteraUpdate = null;
        Optional<Billetera> oBilletera = servicioDAO.findById(id);
        if(!oBilletera.isPresent()){
            //throw new BadRequestException(String.format("No existe."));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("%s con ID %d, no existe.", nombreEntidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        billeteraUpdate = oBilletera.get();
        billeteraUpdate.setSaldoTotal(billetera.getSaldoTotal());

        mensaje.put("datos", servicioDAO.save(billeteraUpdate));
        mensaje.put("success", Boolean.TRUE);

        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{idBilletera}/usuario/{idUsuario}")
    public ResponseEntity<?> asignarBilleteraUsuario(@PathVariable Integer idBilletera, @PathVariable Integer idUsuario){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Billetera> oBilletera = servicioDAO.findById(idBilletera);
        if(!oBilletera.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("%s con ID %d, no existe.", nombreEntidad, idBilletera));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Optional<Usuario> oUsuario = usuarioDAO.findById(idUsuario);
        if(!oUsuario.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Usuario con ID %d, no existe.", idUsuario));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Billetera billetera = oBilletera.get();
        Usuario usuario = oUsuario.get();
        billetera.setUsuario(usuario);

        mensaje.put("datos", servicioDAO.save(billetera));
        mensaje.put("success", Boolean.TRUE);

        return ResponseEntity.ok(mensaje);

    }
}
