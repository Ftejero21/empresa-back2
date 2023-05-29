package com.example.empresaback.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.util.Date;


@Entity
@Table(name = "emails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "emailRemitente")
    private String emailRemitente;



    @Column(name = "nombreDestinatario")
    private String nombreDestinatario;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;

    private File archivoAdjunto;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_content_type")
    private String fileContentType;

    @Lob
    @Column(name = "file_data")
    private byte[] fileData;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresario_id", nullable = false)
    @JsonIgnore()
    private Empresario empresario;
}
