package com.example.empresaback.Controller;

import com.example.empresaback.DTO.CuentasDTO;
import com.example.empresaback.DTO.SucursalDTO;
import com.example.empresaback.Model.Cuentas;
import com.example.empresaback.Model.Sucursal;
import com.example.empresaback.Service.CuentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/cuentas")
public class CuentasController {

    @Autowired
    private CuentasService cuentasService;

    @PostMapping("/{idSucursal}/cuenta")
    public ResponseEntity<Cuentas> crearCuenta(@PathVariable Long idSucursal , @RequestBody CuentasDTO cuentasDTO){
        try {
            Cuentas cuenta = cuentasService.crearCuenta(idSucursal,cuentasDTO);
            return new ResponseEntity<>(cuenta, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{sucursalId}")
    public ResponseEntity<List<Cuentas>> obtenerCuentas(@PathVariable long sucursalId){
        List<Cuentas> sucursales = cuentasService.getCuentaForId(sucursalId);
        return new ResponseEntity<>(sucursales, HttpStatus.OK);
    }
}
