package com.example.empresaback.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CUENTAS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cuentas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GANANCIAS")
    private double ganancias;

    @Column(name = "PERDIDAS")
    private double perdidas;

    @Column(name = "total")
    private double total;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA")
    private Date fecha;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false)
    @JsonIgnore()
    private Sucursal sucursal;

    public void total(){
        this.total = ganancias - perdidas;
    }

    public Cuentas(Double ganancias, Double perdidas) {
        this.ganancias = ganancias;
        this.perdidas = perdidas;
        total();
    }

    public void setGanancias(Double ganancias) {
        this.ganancias = ganancias;
        total();
    }

    public void setPerdidas(Double perdidas) {
        this.perdidas = perdidas;
        total();
    }

}
