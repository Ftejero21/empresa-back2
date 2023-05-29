package com.example.empresaback.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "inventario")
@Getter
@Setter

@NoArgsConstructor
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Lob
    @Column(name ="imagen")
    private String imagen;

    @Column(name = "Cantidad")
    private int cantidad;

    @Column(name = "Precio")
    private double precio;

    @Column(name = "PrecioVenta")
    private double precioVenta;

    @Column(name = "Total")
    private double total;

    @Column(name = "activo", nullable = false,columnDefinition = "BOOLEAN")
    private boolean activo;

    @Column(name = "vendido",nullable = false,columnDefinition = "BOOLEAN")
    private boolean vendido;

    @Column(name = "editable",nullable = false,columnDefinition = "BOOLEAN")
    private boolean editable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false)
    @JsonIgnore()
    private Sucursal sucursal;



    public Inventario(String nombre, Double precio, Integer cantidad,String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = imagen;
       this.total = calcularTotal();
    }

    public Double calcularTotal() {
        this.total = this.precio * this.cantidad;
        return this.total;
    }
}
