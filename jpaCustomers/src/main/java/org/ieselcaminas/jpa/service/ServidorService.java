package org.ieselcaminas.jpa.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ieselcaminas.jpa.entity.Servidor;
import org.ieselcaminas.jpa.repository.ServidorRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;


@Service
 public class ServidorService {
    private final ServidorRepository servidorRepository;

    public ServidorService(ServidorRepository servidorRepository) {
        this.servidorRepository = servidorRepository;
    }


    public Servidor guardarServidor(Servidor servidor) {
        return servidorRepository.save(servidor);
    }

    public List<Servidor> obtenerTodos() {
        return servidorRepository.findAll();
    }

    public void eliminarServidor(Long id) {
        servidorRepository.deleteById(id);
    }


    public String localizarCentroDeDatos(String ip) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://ip-api.com/json/" + ip))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(response.body());

            if (root.path("status").asText().equals("success")) {
                String pais = root.path("country").asText();
                String ciudad = root.path("city").asText();
                String proveedor = root.path("isp").asText();
                return ciudad + ", " + pais + " (ISP: " + proveedor + ")";
            } else {
                return "Ubicación privada o desconocida";
            }
        } catch (Exception e) {
            return "Error de conexión al localizar IP";
        }
    }


    public List<Servidor> filtrarPorEstado(String estadoDeseado) {
        List<Servidor> todosLosServidores = servidorRepository.findAll();

        return todosLosServidores.stream()
                .filter(servidor -> servidor.getEstado().equalsIgnoreCase(estadoDeseado))
                .collect(Collectors.toList());
    }
}
