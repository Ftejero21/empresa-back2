package com.example.empresaback.Repository;

import com.example.empresaback.Model.Cuentas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentasRepository extends JpaRepository<Cuentas,Long> {

    public List<Cuentas> findBySucursalId(Long sucursalId);
}
