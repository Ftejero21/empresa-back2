package com.example.empresaback.Repository;

import com.example.empresaback.Model.Empresario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpresarioRepository extends JpaRepository<Empresario,Long> {

    @Query(value = "SELECT * FROM empresarios  WHERE nombre = :nombre and contrasena = :contrasena" , nativeQuery = true)
    public Empresario loginEmpreario(@Param("nombre") String nombre , @Param("contrasena")String contrasena);
}
