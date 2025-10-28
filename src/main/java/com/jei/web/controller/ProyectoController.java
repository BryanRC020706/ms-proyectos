package com.jei.web.controller;

import com.jei.applicacion.service.ProyectoService;
import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Estado;
import com.jei.web.dto.ProyectoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<List<ProyectoResponseDto>> buscar(@RequestParam(value = "departamento", required = false) String departamentoStr,
                                                            @RequestParam(value = "estado", required = false) Estado estado) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userRole = "ADMIN";
        String userDepartamento = "COMERCIAL";

        if (auth != null && auth.getPrincipal() != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof Jwt jwt) {
                userRole = jwt.getClaimAsString("role");
                userDepartamento = jwt.getClaimAsString("departamento");
            }
        }

        Departamento departamentoFinal;
        if (departamentoStr != null && "ADMIN".equalsIgnoreCase(userRole)) {
            departamentoFinal = Departamento.valueOf(departamentoStr.toUpperCase());
        } else {
            departamentoFinal = Departamento.valueOf(userDepartamento.toUpperCase());
        }

        List<ProyectoResponseDto> issues;
        if (departamentoStr != null && estado != null) {
            issues = proyectoService.buscarPorDepartamentoYEstado(departamentoFinal, estado);
        } else {
            issues = proyectoService.buscarPorDepartamento(departamentoFinal);
        }

        return ResponseEntity.ok(issues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoResponseDto> buscarPorId(@PathVariable Long id) {
        ProyectoResponseDto proyecto = proyectoService.buscarPorId(id);
        return ResponseEntity.ok(proyecto);
    }
}
