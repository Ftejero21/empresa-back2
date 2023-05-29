package com.example.empresaback.Model;

import com.example.empresaback.DTO.EmpleadoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "empleados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "poblacion")
    private String poblacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "codigoPostal")
    private String codigoPostal;

    @Column(name = "Sexo")
    private String sexo;

    @Column(name = "empresarioId")
    private Long empresarioId;

    @Column(name = "email")
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaInicio")
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaFin")
    private Date fechaFin;

    @Column(name = "salario")
    private double salario;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "puesto")
    private String puesto;

    @Column(name = "nif")
    private String nif;

    @Column(name = "horas")
    private Integer horas;

;


    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_content_type")
    private String fileContentType;

    @Lob
    @Column(name = "file_data")
    private byte[] fileData;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false)
    @JsonIgnore()
    private Sucursal sucursal;




}
