package com.jei.web.controller;

import com.jei.applicacion.service.ProyectoService;
import com.jei.web.dto.ProyectoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<List<ProyectoResponseDto>> buscar() {
        List<ProyectoResponseDto> proyecto = proyectoService.buscar();
        return ResponseEntity.ok(proyecto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoResponseDto> buscarPorId(@PathVariable Long id) {
        ProyectoResponseDto proyecto = proyectoService.buscarPorId(id);
        return ResponseEntity.ok(proyecto);
    }
}
