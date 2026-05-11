package org.neomonitor.jpa.service;

import org.neomonitor.jpa.entity.Alerta;
import org.neomonitor.jpa.repository.AlertaRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AlertaService {
    private final AlertaRepository alertaRepository;

    public AlertaService(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    public Alerta guardarAlerta(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    public List<Alerta> obtenerHistorialPorServidor(Long servidorId) {
        List<Alerta> alertas = alertaRepository.findByServidorId(servidorId);

        /* Gracias a que implementamos Comparable, esto ordena automáticamente
         las alertas poniendo las más recientes arriba del todo */
        Collections.sort(alertas);

        return alertas;
    }

    public void eliminarAlerta(Long id) {
        alertaRepository.deleteById(id);
    }
}
