package com.jei.applicacion.service.Impl;

import com.jei.applicacion.client.UsuarioClient;
import com.jei.applicacion.client.UsuarioResponseDto;
import com.jei.applicacion.mapper.ProyectoMapper;
import com.jei.applicacion.service.ProyectoService;
import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Estado;
import com.jei.dominio.entidad.Proyecto;
import com.jei.dominio.repository.ProyectoRepository;
import com.jei.web.dto.ProyectoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
}
