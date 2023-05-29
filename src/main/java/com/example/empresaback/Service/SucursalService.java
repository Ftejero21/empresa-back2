package com.example.empresaback.Service;

import com.example.empresaback.DTO.SucursalDTO;
import com.example.empresaback.Model.Empleado;
import com.example.empresaback.Model.Empresario;
import com.example.empresaback.Model.Inventario;
import com.example.empresaback.Model.Sucursal;
import com.example.empresaback.Repository.EmpresarioRepository;
import com.example.empresaback.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private EmpresarioRepository empresarioRepository;


    private Date getFechaActualSinHora() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }




    public Sucursal crearSucursal(Long empresarioId , SucursalDTO sucursalDTO){
        Empresario empresario = empresarioRepository.findById(empresarioId).orElse(null);
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(sucursalDTO.getNombre());
        sucursal.setCuentas(new ArrayList<>());
        sucursal.setEmpleados(new ArrayList<>());
        sucursal.setDireccion(sucursalDTO.getDireccion());
        sucursal.setEmail(sucursalDTO.getEmail());
        sucursal.setNif(sucursalDTO.getNif());
        sucursal.setWeb(sucursalDTO.getWeb());
        sucursal.setTelefono(sucursalDTO.getTelefono());
        sucursal.setFecha(getFechaActualSinHora());
        sucursal.setImagen(sucursalDTO.getImagen());
        sucursal.setEmpresario(empresario);

        return sucursalRepository.save(sucursal);

    }

    public List<Sucursal> obtenerSucursale(Long empresarioId) {
        return sucursalRepository.findByEmpresarioId(empresarioId);
    }


}
