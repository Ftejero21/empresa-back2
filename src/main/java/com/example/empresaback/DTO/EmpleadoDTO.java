package com.example.empresaback.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO implements Serializable {


    private Long id;

    private String nombre;

    private String apellidos;

    private String provincia;

    private String poblacion;

    private Date fecha;

    private String codigoPostal;

    private String sexo;

    private String email;

    private Long sucursal_id;

    private String rutaImagen;

    private Date fechaInicio;

    private Date fechaFin;

    private double salario;

    private Long empresarioId;

    private String departamento;

    private String puesto;

    private String fileName;

    private String fileContentType;

    private byte[] fileData;

    private String nif;

    private int horas;


}
