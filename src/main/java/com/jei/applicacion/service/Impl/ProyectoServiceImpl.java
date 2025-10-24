package com.jei.applicacion.service.Impl;

import com.jei.applicacion.mapper.ProyectoMapper;
import com.jei.applicacion.service.ProyectoService;
import com.jei.dominio.entidad.Proyecto;
import com.jei.dominio.repository.ProyectoRepository;
import com.jei.web.dto.ProyectoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final ProyectoMapper proyectoMapper;

    @Override
    public List<ProyectoResponseDto> buscar() {
        List<Proyecto> proyecto = proyectoRepository.findAll();
        return proyecto.stream()
                .map(proyectoMapper::toDto)
                .toList();
    }

    @Override
    public ProyectoResponseDto buscarPorId(Long id) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + id));
        return proyectoMapper.toDto(proyecto);
    }
}
