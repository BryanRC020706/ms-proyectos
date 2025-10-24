package com.jei.applicacion.service;

import com.jei.web.dto.ProyectoResponseDto;

import java.util.List;

public interface ProyectoService {
    List<ProyectoResponseDto> buscar();
    ProyectoResponseDto buscarPorId(Long id);
}
