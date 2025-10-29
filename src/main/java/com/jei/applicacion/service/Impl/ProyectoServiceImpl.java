package com.jei.applicacion.service.Impl;

import com.jei.applicacion.client.UsuarioClient;
import com.jei.applicacion.client.UsuarioResponseDto;
import com.jei.applicacion.mapper.ProyectoMapper;
import com.jei.applicacion.service.ProyectoService;
import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Estado;
import com.jei.dominio.entidad.Proyecto;
import com.jei.dominio.repository.ProyectoRepository;
import com.jei.web.dto.ProyectoRequestDto;
import com.jei.web.dto.ProyectoResponseDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final ProyectoMapper proyectoMapper;
    private final UsuarioClient usuarioClient;

    @Override
    public List<ProyectoResponseDto> buscar() {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        return proyectos.stream()
                .map(proyecto -> {
                    ProyectoResponseDto dto = proyectoMapper.toDto(proyecto);

                    if (proyecto.getUsuario() != null) {
                        try {
                            UsuarioResponseDto usuario = usuarioClient.buscarPorId(proyecto.getUsuario());
                            dto.setUsuario(usuario);
                        } catch (Exception e) {
                            System.out.println("No se encontró el usuario con ID: " + proyecto.getUsuario());
                        }
                    }

                    return dto;
                })
                .toList();
    }

    @Override
    public ProyectoResponseDto buscarPorId(Long id) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + id));
        ProyectoResponseDto dto = proyectoMapper.toDto(proyecto);

        if (proyecto.getUsuario() != null) {
            try {
                UsuarioResponseDto usuario = usuarioClient.buscarPorId(proyecto.getUsuario());
                dto.setUsuario(usuario);
            } catch (Exception e) {
                System.out.println("No se encontró el usuario con ID: " + proyecto.getUsuario());
            }
        }

        return dto;
    }

    @Override
    public List<ProyectoResponseDto> buscarPorDepartamentoYEstado(Departamento departamento, Estado estado) {

        return proyectoRepository.findByDepartamentoAndEstado(departamento, estado)
                .stream()
                .map(proyecto -> {
                    ProyectoResponseDto dto = proyectoMapper.toDto(proyecto);

                    if (proyecto.getUsuario() != null) {
                        try {
                            UsuarioResponseDto usuario = usuarioClient.buscarPorId(proyecto.getUsuario());
                            dto.setUsuario(usuario);
                        } catch (Exception e) {
                            System.out.println("No se encontró el usuario con ID: " + proyecto.getUsuario());
                        }
                    }

                    return dto;
                })
                .toList();
    }

    @Override
    public List<ProyectoResponseDto> buscarPorDepartamento(Departamento departamento) {

        return proyectoRepository.findByDepartamento(departamento)
                .stream()
                .map(proyecto -> {
                    ProyectoResponseDto dto = proyectoMapper.toDto(proyecto);

                    if (proyecto.getUsuario() != null) {
                        try {
                            UsuarioResponseDto usuario = usuarioClient.buscarPorId(proyecto.getUsuario());
                            dto.setUsuario(usuario);
                        } catch (Exception e) {
                            System.out.println("No se encontró el usuario con ID: " + proyecto.getUsuario());
                        }
                    }

                    return dto;
                })
                .toList();
    }

    @Override
    public ProyectoResponseDto crear(ProyectoRequestDto proyectoRequest) {
        Proyecto proyecto = proyectoMapper.toDomain(proyectoRequest);

        Proyecto saved = proyectoRepository.save(proyecto);

        ProyectoResponseDto dto = proyectoMapper.toDto(saved);

        if (saved.getUsuario() != null) {
            try {
                UsuarioResponseDto usuario = usuarioClient.buscarPorId(saved.getUsuario());
                dto.setUsuario(usuario);
            } catch (Exception e) {
                System.out.println("No se encontró el usuario con ID: " + saved.getUsuario());
            }
        }

        return dto;
    }

    @Override
    public ProyectoResponseDto editar(Long id, ProyectoRequestDto proyectoRequest) {
        Proyecto existing = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró proyecto con ID: " + id));

        existing.setNombre(proyectoRequest.getNombre());
        existing.setPorcentaje(proyectoRequest.getPorcentaje());
        existing.setEstado(Estado.valueOf(proyectoRequest.getEstado()));
        existing.setDepartamento(Departamento.valueOf(proyectoRequest.getDepartamento()));
        existing.setUsuario(proyectoRequest.getUsuario());
        existing.setFechaCreacion(proyectoRequest.getFechaCreacion());

        Proyecto updated = proyectoRepository.save(existing);

        ProyectoResponseDto dto = proyectoMapper.toDto(updated);

        if (updated.getUsuario() != null) {
            try {
                UsuarioResponseDto usuario = usuarioClient.buscarPorId(updated.getUsuario());
                dto.setUsuario(usuario);
            } catch (Exception e) {
                System.out.println("No se encontró el usuario con ID: " + updated.getUsuario());
            }
        }

        return dto;
    }
}
