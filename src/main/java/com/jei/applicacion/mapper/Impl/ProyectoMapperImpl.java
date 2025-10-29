package com.jei.applicacion.mapper.Impl;

import com.jei.applicacion.mapper.ProyectoMapper;
import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Estado;
import com.jei.dominio.entidad.Proyecto;
import com.jei.web.dto.ProyectoRequestDto;
import com.jei.web.dto.ProyectoResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProyectoMapperImpl implements ProyectoMapper {
    @Override
    public Proyecto toDomain(ProyectoRequestDto proyectoRequestDto) {
        return Proyecto.builder()
                .nombre(proyectoRequestDto.getNombre())
                .porcentaje(proyectoRequestDto.getPorcentaje())
                .estado(Estado.valueOf(proyectoRequestDto.getEstado()))
                .departamento(Departamento.valueOf(proyectoRequestDto.getDepartamento()))
                .usuario(proyectoRequestDto.getUsuario())
                .fechaCreacion(proyectoRequestDto.getFechaCreacion())
                .build();
    }

    @Override
    public ProyectoResponseDto toDto(Proyecto proyecto) {
        return ProyectoResponseDto.builder()
                .id(proyecto.getId())
                .nombre(proyecto.getNombre())
                .porcentaje(proyecto.getPorcentaje())
                .estado(proyecto.getEstado().name())
                .departamento(proyecto.getDepartamento().name())
                .fechaCreacion(proyecto.getFechaCreacion())
                .build();
    }
}
