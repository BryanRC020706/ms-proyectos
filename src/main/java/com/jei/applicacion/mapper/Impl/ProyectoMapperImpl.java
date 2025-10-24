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
                .cuenta(proyectoRequestDto.getCuenta())
                .estado(Estado.valueOf(proyectoRequestDto.getEstado()))
                .departamento(Departamento.valueOf(proyectoRequestDto.getDepartamento()))
                .usuario(proyectoRequestDto.getUsuario())
                .fechaCreacion(proyectoRequestDto.getFechaCreacion())
                .epicas(proyectoRequestDto.getEpicas())
                .build();
    }

    @Override
    public ProyectoResponseDto toDto(Proyecto proyecto) {
        return ProyectoResponseDto.builder()
                .id(proyecto.getId())
                .nombre(proyecto.getNombre())
                .cuenta(proyecto.getCuenta())
                .estado(proyecto.getEstado().name())
                .usuario(proyecto.getUsuario())
                .departamento(proyecto.getDepartamento().name())
                .fechaCreacion(proyecto.getFechaCreacion())
                .epicas(proyecto.getEpicas())
                .build();
    }
}
