package org.ieselcaminas.jpa.repository;

import org.ieselcaminas.jpa.entity.Servidor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServidorRepository extends JpaRepository<Servidor, Long> {
    // Consulta para buscar un servidor por su IP exacta
    Optional<Servidor> findByDireccionIp(String direccionIp);

    // Consulta para buscar servidores por su estado
    List<Servidor> findByEstado(String estado);

    // Consulta para obtener todos los servidores con su administrador asociado
    List<Servidor> findByAdministradorId(Long administradorId);
}