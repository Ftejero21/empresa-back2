package com.example.empresaback.DTO;

import com.example.empresaback.Model.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.io.File;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresarioDTO {

    private Long id;

    private String nombre;

    private String contrasena;

    private String apellidos;

    private Date fecha;

    private String email;

    private String telefono;

    private String fileName;

    private String fileContentType;

    private byte[] fileData;

    private List<SucursalDTO> sucursales;

    private List<Email> emails;
}
