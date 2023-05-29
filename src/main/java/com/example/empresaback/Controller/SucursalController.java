package com.example.empresaback.Controller;

import com.example.empresaback.DTO.SucursalDTO;
import com.example.empresaback.Model.Empleado;
import com.example.empresaback.Model.Inventario;
import com.example.empresaback.Model.Sucursal;
import com.example.empresaback.Service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/sucursal")
public class SucursalController {

    @Autowired
    private SucursalService service;

    @PostMapping("/{empresarioId}/sucursal")
    public ResponseEntity<Sucursal> crearSucursal(@PathVariable Long empresarioId , @RequestBody SucursalDTO sucursalDTO){
        try {
            Sucursal sucursal = service.crearSucursal(empresarioId,sucursalDTO);
            return new ResponseEntity<>(sucursal,HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GetMapping("/{empresarioId}/sucursales")
    public ResponseEntity<List<Sucursal>> obtenerSucursales(@PathVariable Long empresarioId){
        List<Sucursal> sucursales = service.obtenerSucursale(empresarioId);
        return new ResponseEntity<>(sucursales, HttpStatus.OK);
    }
}
