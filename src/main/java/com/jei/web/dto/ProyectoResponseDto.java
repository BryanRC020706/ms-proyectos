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
public class ProyectoResponseDto {
    private Long id;
    private String nombre;
    private String cuenta;
    private String estado;
    private String usuario;
    private String departamento;
    private LocalDate fechaCreacion;
    private String epicas;
}
