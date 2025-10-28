package com.jei.dominio.repository;

import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Estado;
import com.jei.dominio.entidad.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    List<Proyecto> findByDepartamentoAndEstado(Departamento departamento, Estado estado);
    List<Proyecto> findByDepartamento(Departamento departamento);
}
