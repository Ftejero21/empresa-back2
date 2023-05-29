package com.example.empresaback.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDTO {

    private Long id;


    private String nombre;


    private String imagen;


    private int cantidad;


    private double precio;

    private double precioVenta;

    private boolean activo;

    private boolean vendido;

    private boolean editable;


}
