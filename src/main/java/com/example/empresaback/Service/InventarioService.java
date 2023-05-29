package com.example.empresaback.Service;

import com.example.empresaback.DTO.InventarioDTO;
import com.example.empresaback.Model.Cuentas;
import com.example.empresaback.Model.Inventario;
import com.example.empresaback.Model.Sucursal;
import com.example.empresaback.Repository.InventarioRepository;
import com.example.empresaback.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private InventarioRepository inventarioRepository;


    public Inventario crearObjeto(Long sucursalId , InventarioDTO inventarioDTO){
        Sucursal sucursal = sucursalRepository.findById(sucursalId).orElseThrow(() -> new NoSuchElementException("No se encontró la sucursal con el id " + sucursalId));
        Inventario inventario = new Inventario();

        inventario.setNombre(inventarioDTO.getNombre());
        inventario.setCantidad(inventarioDTO.getCantidad());
        inventario.setImagen(inventarioDTO.getImagen());
        inventario.setPrecio(inventarioDTO.getPrecio());
        inventario.setPrecioVenta(inventarioDTO.getPrecioVenta());
        inventario.setSucursal(sucursal);
        inventario.setActivo(false);
        inventario.setVendido(false);
        inventario.setEditable(false);
        inventario.calcularTotal();



        return inventarioRepository.save(inventario);
    }

    public Inventario tramitarCompra(Long inventarioId){
        Optional<Inventario> personaOptional = inventarioRepository.findById(inventarioId);
        if (personaOptional.isPresent()) {
            Inventario persona = personaOptional.get();
            persona.setActivo(true);
            return inventarioRepository.save(persona);
        }
        return null;
    }

    public Inventario anularCompra(Long inventarioId){
        Optional<Inventario> personaOptional = inventarioRepository.findById(inventarioId);
        if (personaOptional.isPresent()) {
            Inventario persona = personaOptional.get();
            persona.setActivo(false); // actualizamos el valor a true
            return inventarioRepository.save(persona);
        }
        return null;
    }

    public Inventario venderObjeto(Long inventarioId){
        Optional<Inventario> personaOptional = inventarioRepository.findById(inventarioId);
        if (personaOptional.isPresent()) {
            Inventario persona = personaOptional.get();
            persona.setVendido(true); // actualizamos el valor a true
            return inventarioRepository.save(persona);
        }
        return null;
    }

    public Inventario actualizarCompra(Long inventarioId,InventarioDTO inventarioDTO){
        Optional<Inventario> optionalProducto = inventarioRepository.findById(inventarioId);

        if (optionalProducto.isPresent()) {
            Inventario inventario = optionalProducto.get();
            inventario.setNombre(inventarioDTO.getNombre());
            inventario.setPrecio(inventarioDTO.getPrecio());
            inventario.setPrecioVenta(inventarioDTO.getPrecioVenta());
            inventario.setCantidad(inventarioDTO.getCantidad());
            inventario.calcularTotal();
           return inventarioRepository.save(inventario);
        } else {
            throw new RuntimeException("Producto no encontrado con el ID: " + inventarioId);
        }
    }


    public void deleteObjeto(Long id){
        inventarioRepository.deleteById(id);
    }

    public List<Inventario> getObjetosActivos(Long id){
        return inventarioRepository.getObjetosActivos(id);
    }

    public List<Inventario> getInventarioForId(long sucursalId){
        return inventarioRepository.findBySucursalId(sucursalId);
    }

    public List<Object[]> obtenerInventarioActivoConNombreSucursal(Long empresarioId) {
        return inventarioRepository.obtenerInventarioActivoConNombreSucursal(empresarioId);
    }


}
