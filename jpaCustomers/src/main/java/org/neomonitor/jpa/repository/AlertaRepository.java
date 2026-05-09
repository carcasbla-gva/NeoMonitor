package org.neomonitor.jpa.repository;

import org.neomonitor.jpa.entity.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    // Consulta para filtrar alertas por su gravedad
    List<Alerta> findByNivelGravedad(String nivelGravedad);

    // Consulta para sacar el historial de un servidor en concreto
    List<Alerta> findByServidorId(Long servidorId);
}