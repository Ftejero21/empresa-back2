package com.example.empresaback.Controller;


import com.example.empresaback.DTO.EmpleadoDTO;
import com.example.empresaback.Model.Empleado;
import com.example.empresaback.Model.Sucursal;
import com.example.empresaback.Repository.EmpleadoRepository;
import com.example.empresaback.Repository.SucursalRepository;
import com.example.empresaback.Service.EmpleadoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @PostMapping(value = "/{sucursalId}/empleados/{empresarioId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Empleado> crearEmpleado(@PathVariable("sucursalId") Long sucursalId,
                                                  @RequestParam("nombre") String nombre,
                                                  @RequestParam("apellidos") String apellidos,
                                                  @RequestParam("provincia") String provincia,
                                                  @RequestParam("poblacion") String poblacion,
                                                  @RequestParam("fecha") String fechaStr,
                                                  @RequestParam("codigoPostal") String codigoPostal,
                                                  @RequestParam("sexo") String sexo,
                                                  @RequestParam("email") String email,
                                                  @RequestParam("fechaInicio") String fechaInicioStr,
                                                  @RequestParam("fechaFin") String fechaFinStr,
                                                  @RequestParam("salario") Double salario,
                                                  @RequestParam("departamento") String departamento,
                                                  @RequestParam("puesto") String puesto,
                                                  @RequestParam("empresarioId") Long empresarioId,
                                                  @RequestParam("nif") String nif,
                                                  @RequestParam("horas") Integer horas,
                                                  @RequestParam(value = "archivoAdjunto", required = false) MultipartFile archivoAdjunto) throws IOException, ParseException {

        Date fecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(fechaStr);
        Date fechaInicio = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(fechaInicioStr);
        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(fechaFinStr);

        Sucursal sucursal = sucursalRepository.findById(sucursalId).orElse(null);

        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setApellidos(apellidos);
        empleado.setProvincia(provincia);
        empleado.setPoblacion(poblacion);
        empleado.setFecha(fecha);
        empleado.setCodigoPostal(codigoPostal);
        empleado.setSexo(sexo);
        empleado.setEmail(email);
        empleado.setFechaInicio(fechaInicio);
        empleado.setFechaFin(fechaFin);
        empleado.setSalario(salario);
        empleado.setDepartamento(departamento);

        empleado.setHoras(horas);
        empleado.setNif(nif);
        empleado.setPuesto(puesto);
        empleado.setEmpresarioId(empresarioId);
        empleado.setSucursal(sucursal);

        if (archivoAdjunto != null) {
            empleado.setFileName(archivoAdjunto.getOriginalFilename());
            empleado.setFileContentType(archivoAdjunto.getContentType());
            empleado.setFileData(archivoAdjunto.getBytes());
        }

        empleadoRepository.save(empleado);

        return new ResponseEntity<>(empleado, HttpStatus.CREATED);
    }

    @GetMapping("/{sucursalId}/empleadosSucursal")
    public ResponseEntity<List<Empleado>> obtenerEmpleados(@PathVariable Long sucursalId){
        List<Empleado> empleados = empleadoService.obtenerEmpleados(sucursalId);
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/{empresarioId}/empleados")
    public ResponseEntity<List<Empleado>> obtenerEmpleadosByEmpresarioId(@PathVariable Long empresarioId){
        List<Empleado> empleados = empleadoService.ObtenerEmpleadoByEmpresarioId(empresarioId);
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/empleados/{empleadoId}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable Long empleadoId) {
        Empleado empleado = empleadoService.obtenerEmpleado(empleadoId);
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }

    @PutMapping("/empleados/{empleadoId}")
    public ResponseEntity<Empleado> actualizarEmpleado(
                                                       @PathVariable("empleadoId") Long empleadoId,
                                                       @RequestParam("nombre") String nombre,
                                                       @RequestParam("apellidos") String apellidos,
                                                       @RequestParam("provincia") String provincia,
                                                       @RequestParam("poblacion") String poblacion,
                                                       @RequestParam("fecha") String fechaStr,
                                                       @RequestParam("codigoPostal") String codigoPostal,
                                                       @RequestParam("sexo") String sexo,
                                                       @RequestParam("email") String email,
                                                       @RequestParam("fechaInicio") String fechaInicioStr,
                                                       @RequestParam("fechaFin") String fechaFinStr,
                                                       @RequestParam("salario") Double salario,
                                                       @RequestParam("departamento") String departamento,
                                                       @RequestParam("puesto") String puesto,
                                                       @RequestParam("nif") String nif,
                                                       @RequestParam("horas") Integer horas,
                                                       @RequestParam(value = "archivoAdjunto", required = false) MultipartFile archivoAdjunto) throws IOException, ParseException {

        Date fecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(fechaStr);
        Date fechaInicio = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(fechaInicioStr);
        Date fechaFin = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(fechaFinStr);



        Empleado empleado = empleadoRepository.findById(empleadoId).orElse(null);
        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        empleado.setNombre(nombre);
        empleado.setApellidos(apellidos);
        empleado.setProvincia(provincia);
        empleado.setPoblacion(poblacion);
        empleado.setFecha(fecha);
        empleado.setCodigoPostal(codigoPostal);
        empleado.setSexo(sexo);
        empleado.setEmail(email);
        empleado.setFechaInicio(fechaInicio);
        empleado.setFechaFin(fechaFin);
        empleado.setSalario(salario);
        empleado.setDepartamento(departamento);
        empleado.setPuesto(puesto);
        empleado.setHoras(horas);
        empleado.setNif(nif);


        if (archivoAdjunto != null) {
            empleado.setFileName(archivoAdjunto.getOriginalFilename());
            empleado.setFileContentType(archivoAdjunto.getContentType());
            empleado.setFileData(archivoAdjunto.getBytes());
        }

        empleadoRepository.save(empleado);

        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }

    @DeleteMapping("/empleados/{empleadoId}")
    public ResponseEntity<Void> borrarEmpleado(@PathVariable Long empleadoId) {
        empleadoService.borrarEmpleado(empleadoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
