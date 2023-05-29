package com.example.empresaback.Repository;

import com.example.empresaback.Model.Empleado;
import com.example.empresaback.Model.Inventario;
import com.example.empresaback.Model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SucursalRepository extends JpaRepository<Sucursal,Long> {
    public List<Sucursal> findByEmpresarioId(Long empresarioId);


}
