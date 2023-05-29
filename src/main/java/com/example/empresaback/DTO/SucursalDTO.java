package com.example.empresaback.DTO;

import com.example.empresaback.Model.Empresario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SucursalDTO {

    private String nombre;

    private String direccion;

    private String web;

    private String telefono;

    private String nif;

    private String email;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha;

    private Empresario empresario;

    private String imagen;

    private List<CuentasDTO> ganancias;

    private List<InventarioDTO> inventario;

    private List<EmpleadoDTO> empleados;


}
