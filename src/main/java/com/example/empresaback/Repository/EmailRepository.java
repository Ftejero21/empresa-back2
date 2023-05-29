package com.example.empresaback.Repository;

import com.example.empresaback.Model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email,Long> {

    public List<Email> findByEmpresarioIdOrderByIdDesc(Long empresarioId);

    @Query(value = "SELECT DISTINCT email FROM emails WHERE email = :email", nativeQuery = true)
    public List<Email> findByEmail(@Param("email") String email);
}
