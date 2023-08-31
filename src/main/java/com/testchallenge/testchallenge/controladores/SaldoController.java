package com.testchallenge.testchallenge.controladores;

import com.testchallenge.testchallenge.modelo.entidades.Billetera;
import com.testchallenge.testchallenge.modelo.entidades.Divisa;
import com.testchallenge.testchallenge.modelo.entidades.Saldo;
import com.testchallenge.testchallenge.servicios.contratos.BilleteraDAO;
import com.testchallenge.testchallenge.servicios.contratos.DivisaDAO;
import com.testchallenge.testchallenge.servicios.contratos.SaldoDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/saldos")
public class SaldoController extends GenericController<Saldo, SaldoDAO>{

    private final BilleteraDAO billeteraDAO;
    private final DivisaDAO divisaDAO;

    @Autowired
    public SaldoController(SaldoDAO servicioDAO, BilleteraDAO billeteraDAO, DivisaDAO divisaDAO) {
        super(servicioDAO);
        this.billeteraDAO = billeteraDAO;
        this.divisaDAO = divisaDAO;
        nombreEntidad="saldo";
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> saldoUpdate(@PathVariable Integer id, @Valid @RequestBody Saldo saldo, BindingResult result){
        Map<String, Object> validaciones = new HashMap<>();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(
                    error-> validaciones.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(validaciones);
        }

        Map<String, Object> mensaje = new HashMap<>();
        Saldo saldoUpdate = null;
        Optional<Saldo> oSaldo = servicioDAO.findById(id);
        if(!oSaldo.isPresent()){
            //throw new BadRequestException(String.format("No existe."));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("%s con ID %d, no existe.", nombreEntidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        saldoUpdate = oSaldo.get();
        saldoUpdate.setSaldo(saldo.getSaldo());

        mensaje.put("datos", servicioDAO.save(saldoUpdate));
        mensaje.put("success", Boolean.TRUE);

        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{idSaldo}/divisa/{idDivisa}/billetera/{idBilletera}")
    public ResponseEntity<?> asignarSaldoDivisaBilletera(@PathVariable Integer idSaldo, @PathVariable Integer idDivisa, @PathVariable Integer idBilletera){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Saldo> oSaldo = servicioDAO.findById(idSaldo);
        if(!oSaldo.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("%s con ID %d, no existe.", nombreEntidad, idSaldo));
            return ResponseEntity.badRequest().body(mensaje);

        }
        Optional<Divisa> oDivisa = divisaDAO.findById(idDivisa);
        if(!oDivisa.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Divisa con ID %d, no existe.", idDivisa));
            return ResponseEntity.badRequest().body(mensaje);

        }
        Optional<Billetera> oBilletera = billeteraDAO.findById(idBilletera);
        if(!oBilletera.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Billetera con ID %d, no existe.", idBilletera));
            return ResponseEntity.badRequest().body(mensaje);

        }
        Saldo saldo = oSaldo.get();
        Divisa divisa = oDivisa.get();
        Billetera billetera = oBilletera.get();
        saldo.setDivisa(divisa);
        saldo.setBilletera(billetera);
        mensaje.put("datos", servicioDAO.save(saldo));
        mensaje.put("success", Boolean.TRUE);

        return ResponseEntity.ok(mensaje);
    }
}
