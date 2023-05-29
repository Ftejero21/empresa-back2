package com.example.empresaback.Service;


import com.example.empresaback.DTO.EmpresarioDTO;
import com.example.empresaback.Model.Empresario;
import com.example.empresaback.Repository.EmpresarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EmpresarioService {

    @Autowired
    private EmpresarioRepository empresarioRepository;


    public List<Empresario> obtenerEmpresarios() {
        return empresarioRepository.findAll();
    }

    public Empresario obtenerEmpresario(Long empresarioId) {
        return empresarioRepository.findById(empresarioId).orElseThrow(() -> new EntityNotFoundException("Empresario no encontrado con id: " + empresarioId));
    }

    public EmpresarioDTO loginEmpresario(String nombre , String contrasena){
        Empresario empresario = empresarioRepository.loginEmpreario(nombre , contrasena);
        if (empresario == null) {
            return null;
        }
        EmpresarioDTO empresarioDTO = new EmpresarioDTO();

        empresarioDTO.setNombre(empresario.getNombre());
        empresarioDTO.setContrasena(empresario.getContrasena());
        empresarioDTO.setApellidos(empresario.getApellidos());
        empresarioDTO.setFecha(empresario.getFecha());
        empresarioDTO.setId(empresario.getId());
        empresarioDTO.setEmail(empresario.getEmail());
        empresarioDTO.setTelefono(empresario.getTelefono());
        return empresarioDTO;
    }

    public Empresario actualizarEmpresario(Long empresarioId, EmpresarioDTO empresarioDTO, MultipartFile archivoAdjunto) throws IOException, ChangeSetPersister.NotFoundException {

        Empresario empresario = empresarioRepository.findById(empresarioId).orElse(null);
        if(empresario == null){
            throw new ChangeSetPersister.NotFoundException();
        }


        empresario.setNombre(empresarioDTO.getNombre());
        empresario.setContrasena(empresarioDTO.getContrasena());
        empresario.setApellidos(empresarioDTO.getApellidos());
        empresario.setFecha(empresarioDTO.getFecha());

        if (archivoAdjunto != null) {
            empresario.setFileName(archivoAdjunto.getOriginalFilename());
            empresario.setFileContentType(archivoAdjunto.getContentType());
            empresario.setFileData(archivoAdjunto.getBytes());
        }

        return empresarioRepository.save(empresario);
    }

    public void borrarEmpresario(Long empresarioId) {
        Empresario empresario = obtenerEmpresario(empresarioId);
        empresarioRepository.delete(empresario);
    }
}
