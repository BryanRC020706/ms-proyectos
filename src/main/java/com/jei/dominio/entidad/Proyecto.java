package com.jei.dominio.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Proyecto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int porcentaje;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private Long usuario;
    @Enumerated(EnumType.STRING)
    private Departamento departamento;
    private LocalDate fechaCreacion;
}
