package com.example.empresaback.Controller;

import com.example.empresaback.DTO.EmpleadoDTO;
import com.example.empresaback.DTO.InventarioDTO;
import com.example.empresaback.Model.Cuentas;
import com.example.empresaback.Model.Empleado;
import com.example.empresaback.Model.Inventario;
import com.example.empresaback.Model.Sucursal;
import com.example.empresaback.Repository.InventarioRepository;
import com.example.empresaback.Service.InventarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/inventario")
public class InventarioController {


    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/{sucursalId}")
    public ResponseEntity<Inventario> crearObjeto(@PathVariable Long sucursalId, @RequestBody InventarioDTO inventarioDTO) {
        try {
            Inventario inventario = inventarioService.crearObjeto(sucursalId, inventarioDTO);
            return new ResponseEntity<>(inventario, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{sucursalId}")
    public ResponseEntity<List<Inventario>> obtenerCuentas(@PathVariable Long sucursalId){
        List<Inventario> inventarios = inventarioService.getInventarioForId(sucursalId);
        return new ResponseEntity<>(inventarios, HttpStatus.OK);
    }

    @GetMapping("/{Id}/activos")
    public ResponseEntity<List<Inventario>> obtenerObjetos(@PathVariable Long Id){
        List<Inventario> inventarios = inventarioService.getObjetosActivos(Id);
        return new ResponseEntity<>(inventarios, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> actualizarActiu(@PathVariable Long id) {
        Inventario inventario = inventarioService.tramitarCompra(id);
        if (inventario == null) {
            return ResponseEntity.notFound().build();
        }
        InventarioDTO inventarioDTO = modelMapper.map(inventario, InventarioDTO.class);
        return ResponseEntity.ok(inventarioDTO);
    }

    @PutMapping("vender/{id}")
    public ResponseEntity<InventarioDTO> vender(@PathVariable Long id) {
        Inventario inventario = inventarioService.venderObjeto(id);
        if (inventario == null) {
            return ResponseEntity.notFound().build();
        }
        InventarioDTO inventarioDTO = modelMapper.map(inventario, InventarioDTO.class);
        return ResponseEntity.ok(inventarioDTO);
    }

    @PutMapping("/{id}/anular")
    public ResponseEntity<InventarioDTO> anularCompra(@PathVariable Long id) {
        Inventario inventario = inventarioService.anularCompra(id);
        if (inventario == null) {
            return ResponseEntity.notFound().build();
        }
        InventarioDTO inventarioDTO = modelMapper.map(inventario, InventarioDTO.class);
        return ResponseEntity.ok(inventarioDTO);
    }

    @GetMapping("/activo/{empresarioId}")
    public ResponseEntity<List<Object[]>> obtenerInventarioActivoConNombreSucursal(@PathVariable Long empresarioId) {
        List<Object[]> inventarioActivo = inventarioService.obtenerInventarioActivoConNombreSucursal(empresarioId);
        return ResponseEntity.ok(inventarioActivo);
    }

    @PutMapping("/{id}/actualizar")
    public ResponseEntity<?> actualizarObjeto(@PathVariable Long id, @RequestBody InventarioDTO inventarioDTO) {
        try {
            inventarioService.actualizarCompra(id, inventarioDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteObjeto(@PathVariable Long id){
         inventarioService.deleteObjeto(id);
    }

}
