package com.example.empresaback.Repository;

import com.example.empresaback.Model.Cuentas;
import com.example.empresaback.Model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario,Long> {

    @Query(value = "SELECT * FROM INVENTARIO WHERE sucursal_id = :ID AND ACTIVO = 1 AND vendido = 0" , nativeQuery = true)
    public List<Inventario> getObjetosActivos(@Param("ID") Long id);

    public List<Inventario> findBySucursalId(Long sucursalId);

    @Query("SELECT i, s.nombre , s.id FROM Inventario i JOIN i.sucursal s WHERE i.activo = 1 AND s.empresario.id = :empresarioId")
    List<Object[]> obtenerInventarioActivoConNombreSucursal(@Param("empresarioId") Long empresarioId);
}
