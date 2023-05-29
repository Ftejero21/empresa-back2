package com.example.empresaback.Repository;

import com.example.empresaback.Model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {

    public List<Empleado> findBySucursalId(Long empresarioId);



    public List<Empleado> findEmpleadoByEmpresarioId(Long empresarioId);
}
