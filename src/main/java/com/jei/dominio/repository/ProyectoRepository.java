package com.jei.dominio.repository;

import com.jei.dominio.entidad.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}
