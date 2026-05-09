package org.neomonitor.jpa.repository;

import org.neomonitor.jpa.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    // Consulta para poder buscar el administrador por su correo
    Optional<Administrador> findByEmail(String email);
}