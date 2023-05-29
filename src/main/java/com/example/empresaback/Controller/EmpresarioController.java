package com.example.empresaback.Controller;


import com.example.empresaback.DTO.EmpresarioDTO;
import com.example.empresaback.Model.Empresario;
import com.example.empresaback.Repository.EmpresarioRepository;
import com.example.empresaback.Service.EmpresarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/empresarios")
@CrossOrigin(origins = "*")
public class EmpresarioController {

    @Autowired
    private EmpresarioService empresarioService;

    @Autowired
    private EmpresarioRepository empresarioRepository;


    @PostMapping("/create")
    public ResponseEntity<Empresario> crearEmpresario(@RequestParam("nombre") String nombre,
                                                      @RequestParam("contrasena") String contrasena,
                                                      @RequestParam("apellidos") String apellidos,
                                                      @RequestParam("email") String email,
                                                      @RequestParam("telefono") String telefono,
                                                      @RequestParam("fecha")String fechaStr,
                                                      @RequestParam(value = "archivoAdjunto", required = false) MultipartFile archivoAdjunto) throws IOException, ParseException {
        Empresario empresarioDTO = new Empresario();
        empresarioDTO.setNombre(nombre);
        empresarioDTO.setContrasena(contrasena);
        empresarioDTO.setApellidos(apellidos);
        empresarioDTO.setEmail(email);
        empresarioDTO.setTelefono(telefono);
        Date fecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(fechaStr);
        empresarioDTO.setFecha(fecha);

        if (archivoAdjunto != null) {

            empresarioDTO.setFileName(archivoAdjunto.getOriginalFilename());
            empresarioDTO.setFileContentType(archivoAdjunto.getContentType());
            empresarioDTO.setFileData(archivoAdjunto.getBytes());
        }

        empresarioRepository.save(empresarioDTO);

        if (archivoAdjunto != null) { // Agregamos la lógica para el archivo adjunto
            DataSource dataSource = new ByteArrayDataSource(archivoAdjunto.getBytes(), archivoAdjunto.getContentType());

        }


        return new ResponseEntity<>(empresarioDTO, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Empresario>> obtenerEmpresarios() {
        try {
            List<Empresario> empresarios = empresarioService.obtenerEmpresarios();
            return new ResponseEntity<>(empresarios, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GetMapping("/login/{nombre}/{contrasena}")
    public EmpresarioDTO login(@PathVariable String nombre, @PathVariable String contrasena) {
        EmpresarioDTO empresarioDTO = empresarioService.loginEmpresario(nombre , contrasena);
        if (empresarioDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresario no encontrado");
        }
        return empresarioDTO;
    }

    @GetMapping("/{empresarioId}")
    public ResponseEntity<Empresario> obtenerEmpresario(@PathVariable Long empresarioId) {
        Empresario empresario = empresarioService.obtenerEmpresario(empresarioId);
        return new ResponseEntity<>(empresario, HttpStatus.OK);
    }

    @PutMapping("/{empresarioId}")
    public ResponseEntity<Empresario> actualizarEmpresario(
            @PathVariable("empresarioId") Long empresarioId,
            @RequestParam("nombre") String nombre,
            @RequestParam("contrasena") String contrasena,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("email") String email,
            @RequestParam("telefono") String telefono,
            @RequestParam("fecha") String fechaStr,
            @RequestParam(value = "archivoAdjunto", required = false) MultipartFile archivoAdjunto) throws IOException, ParseException {

        Date fecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(fechaStr);

        Empresario empresario = empresarioRepository.findById(empresarioId).orElse(null);
        if (empresario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        empresario.setNombre(nombre);
        empresario.setContrasena(contrasena);
        empresario.setApellidos(apellidos);
        empresario.setFecha(fecha);
        empresario.setEmail(email);
        empresario.setTelefono(telefono);

        if (archivoAdjunto != null) {
            empresario.setFileName(archivoAdjunto.getOriginalFilename());
            empresario.setFileContentType(archivoAdjunto.getContentType());
            empresario.setFileData(archivoAdjunto.getBytes());
        }

        empresarioRepository.save(empresario);

        return new ResponseEntity<>(empresario, HttpStatus.OK);
    }

    @DeleteMapping("/{empresarioId}")
    public ResponseEntity<Void> borrarEmpresario(@PathVariable Long empresarioId) {
        empresarioService.borrarEmpresario(empresarioId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
