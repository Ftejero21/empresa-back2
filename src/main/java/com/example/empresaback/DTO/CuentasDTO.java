package com.example.empresaback.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuentasDTO {

    private Long id;

    private double ganancias;

    private double perdidas;

    private double total;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha;


}
