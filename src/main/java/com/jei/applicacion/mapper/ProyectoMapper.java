package com.jei.applicacion.mapper;

import com.jei.dominio.entidad.Proyecto;
import com.jei.web.dto.ProyectoRequestDto;
import com.jei.web.dto.ProyectoResponseDto;

public interface ProyectoMapper {
    Proyecto toDomain(ProyectoRequestDto proyectoRequestDto);
    ProyectoResponseDto toDto(Proyecto proyecto);
}
