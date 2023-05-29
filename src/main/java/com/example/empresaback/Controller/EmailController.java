package com.example.empresaback.Controller;

import com.example.empresaback.Model.Email;
import com.example.empresaback.Model.Empresario;
import com.example.empresaback.Model.Sucursal;
import com.example.empresaback.Repository.EmailRepository;
import com.example.empresaback.Repository.EmpresarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/email")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmpresarioRepository empresarioRepository;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestParam("email") String email,
                                            @RequestParam(value = "emailRemitente" , required = false) String emailRemitente,
                                            @RequestParam(value = "nombreDestinatario" , required = false) String nombreDestinatario,
                                            @RequestParam("titulo") String titulo,
                                            @RequestParam("mensaje") String mensaje,
                                            @RequestParam("nombre") String nombre,
                                            @RequestParam("empresarioId") Long empresarioId,
                                            @RequestParam("fecha")String fechaStr,
                                            @RequestParam(value = "archivoAdjunto", required = false) MultipartFile archivoAdjunto) throws MessagingException, IOException, ParseException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Empresario empresario = empresarioRepository.findById(empresarioId).orElse(null);
        Date fecha = new SimpleDateFormat("dd-MM-yyyy").parse(fechaStr);

        Email emailEntity = new Email();
        emailEntity.setEmail(email);
        emailEntity.setTitulo(titulo);
        emailEntity.setMensaje(mensaje);
        emailEntity.setEmpresario(empresario);
        emailEntity.setFecha(fecha);
        emailEntity.setNombre(nombre);
        emailEntity.setEmailRemitente(emailRemitente);
        emailEntity.setNombreDestinatario(nombreDestinatario);


        if (archivoAdjunto != null) {
            emailEntity.setFileName(archivoAdjunto.getOriginalFilename());
            emailEntity.setFileContentType(archivoAdjunto.getContentType());
            emailEntity.setFileData(archivoAdjunto.getBytes());
        }

        emailRepository.save(emailEntity);

        helper.setTo(email);
        helper.setSubject(titulo);
        helper.setText(mensaje);

        if (archivoAdjunto != null) { // Agregamos la lógica para el archivo adjunto
            DataSource dataSource = new ByteArrayDataSource(archivoAdjunto.getBytes(), archivoAdjunto.getContentType());
            helper.addAttachment(archivoAdjunto.getOriginalFilename(), dataSource);
        }

        javaMailSender.send(message);

        return ResponseEntity.ok("Correo Electrónico Enviado Correctamente");
    }

    @GetMapping("/{empresarioId}/emails")
    public ResponseEntity<List<Email>> getEmails(@PathVariable Long empresarioId){
        List<Email> emails = emailRepository.findByEmpresarioIdOrderByIdDesc(empresarioId);
        emails = emails.stream().filter(distinctByKey(Email::getEmail)).collect(Collectors.toList());
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @GetMapping("emailsRecibidos/{email}")
    public ResponseEntity<List<Email>> getEmailsRecibidos(@PathVariable String email){
        List<Email> emails = emailRepository.findByEmail(email);
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }

    @DeleteMapping("deleteEmail/{id}")
    public ResponseEntity<Void> borrarEmail(@PathVariable Long id) {
        emailRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
