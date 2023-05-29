package com.example.empresaback.Service;

import com.example.empresaback.DTO.EmpleadoDTO;
import com.example.empresaback.Model.Empleado;
import com.example.empresaback.Model.Empresario;
import com.example.empresaback.Model.Sucursal;
import com.example.empresaback.Repository.EmpleadoRepository;
import com.example.empresaback.Repository.EmpresarioRepository;
import com.example.empresaback.Repository.SucursalRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class EmpleadoService {

    @Autowired
    private EmpresarioRepository empresarioRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;






    public List<Empleado> obtenerEmpleados(Long sucursalId) {
        return empleadoRepository.findBySucursalId(sucursalId);
    }

    public List<Empleado> ObtenerEmpleadoByEmpresarioId(Long empresarioId){
        return empleadoRepository.findEmpleadoByEmpresarioId(empresarioId);
    }

    public Empleado obtenerEmpleado(Long empleadoId) {
        return empleadoRepository.findById(empleadoId).orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con id: " + empleadoId));
    }



    public Empleado actualizarEmpleado(Long empleadoId, EmpleadoDTO empleadoDTO) {
        Empleado empleado = obtenerEmpleado(empleadoId);
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setApellidos(empleadoDTO.getApellidos());
        empleado.setProvincia(empleadoDTO.getProvincia());
        empleado.setPoblacion(empleadoDTO.getPoblacion());
        empleado.setFecha(empleadoDTO.getFecha());
        empleado.setCodigoPostal(empleadoDTO.getCodigoPostal());
        empleado.setSexo(empleadoDTO.getSexo());
        empleado.setEmail(empleadoDTO.getEmail());
        empleado.setFechaInicio(empleadoDTO.getFechaInicio());
        empleado.setFechaFin(empleadoDTO.getFechaFin());

        empleado.setSalario(empleadoDTO.getSalario());
        empleado.setDepartamento(empleadoDTO.getDepartamento());
        empleado.setPuesto(empleadoDTO.getPuesto());
        return empleadoRepository.save(empleado);
    }

    public void borrarEmpleado(Long empleadoId) {
        Empleado empleado = obtenerEmpleado(empleadoId);
        empleadoRepository.delete(empleado);
    }

    public Empresario obtenerEmpresario(Long empresarioId) {
        return empresarioRepository.findById(empresarioId).orElseThrow(() -> new EntityNotFoundException("Empresario no encontrado con id: " + empresarioId));
    }
}
