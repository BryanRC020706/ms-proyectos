package com.jei.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProyectoRequestDto {
    private String nombre;
    private int porcentaje;
    private String estado;
    private Long usuario;
    private String departamento;
    private LocalDate fechaCreacion;
}
