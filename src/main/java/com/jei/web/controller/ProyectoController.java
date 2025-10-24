package com.jei.web.controller;

import com.jei.applicacion.service.ProyectoService;
import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Estado;
import com.jei.web.dto.ProyectoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<List<ProyectoResponseDto>> buscar(@RequestParam(value = "departamento", required = false) Departamento departamento,
                                                            @RequestParam(value = "estado", required = false) Estado estado) {

        List<ProyectoResponseDto> issues;

        if (departamento != null && estado != null) {
            issues = proyectoService.buscarPorDepartamentoYEstado(departamento, estado);
        }
        else {
            issues = proyectoService.buscar();
        }

        return ResponseEntity.ok(issues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoResponseDto> buscarPorId(@PathVariable Long id) {
        ProyectoResponseDto proyecto = proyectoService.buscarPorId(id);
        return ResponseEntity.ok(proyecto);
    }
}
