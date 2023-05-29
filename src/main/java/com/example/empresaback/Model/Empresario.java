package com.example.empresaback.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "empresarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empresario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos",nullable = false)
    private String apellidos;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;


    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_content_type")
    private String fileContentType;

    @Lob
    @Column(name = "file_data")
    private byte[] fileData;




    @Column(name = "contrasena", nullable = false)
    private String contrasena;



    @OneToMany(mappedBy = "empresario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sucursal> sucursales;

    @OneToMany(mappedBy = "empresario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Email> emails;
}
