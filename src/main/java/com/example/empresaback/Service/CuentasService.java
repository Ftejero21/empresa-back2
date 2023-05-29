package com.example.empresaback.Service;


import com.example.empresaback.DTO.CuentasDTO;
import com.example.empresaback.Model.Cuentas;
import com.example.empresaback.Model.Sucursal;
import com.example.empresaback.Repository.CuentasRepository;
import com.example.empresaback.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CuentasService {


    @Autowired
    private CuentasRepository cuentasRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    private Date getFechaActualSinHora() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public Cuentas crearCuenta(Long idSucursal, CuentasDTO cuentaDTO) {
        Sucursal sucursal = sucursalRepository.findById(idSucursal).orElseThrow(() -> new NoSuchElementException("No se encontró la sucursal con el id " + idSucursal));
        Cuentas cuenta = new Cuentas();
        cuenta.setGanancias(cuentaDTO.getGanancias());
        cuenta.setPerdidas(cuentaDTO.getPerdidas());
        cuenta.setSucursal(sucursal);
        cuenta.setFecha(getFechaActualSinHora());
        return cuentasRepository.save(cuenta);
    }

    public List<Cuentas> getCuentaForId(long sucursalId){
        return cuentasRepository.findBySucursalId(sucursalId);
    }
}
