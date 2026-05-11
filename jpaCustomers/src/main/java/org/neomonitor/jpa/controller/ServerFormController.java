package org.neomonitor.jpa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.neomonitor.jpa.entity.Administrador;
import org.neomonitor.jpa.entity.Servidor;
import org.neomonitor.jpa.repository.AdministradorRepository;
import org.neomonitor.jpa.service.ServidorService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServerFormController {

    @FXML private TextField ipField;
    @FXML private TextField osField;
    @FXML private ComboBox<String> statusCombo;
    @FXML private TextField adminIdField;

    private final ServidorService servidorService;
    private final AdministradorRepository administradorRepository;

    public ServerFormController(ServidorService servidorService, AdministradorRepository administradorRepository) {
        this.servidorService = servidorService;
        this.administradorRepository = administradorRepository;
    }

    @FXML
    public void initialize() {
        statusCombo.getItems().addAll("Online", "Mantenimiento", "Caído");
    }

    @FXML
    private void handleSave() {
        try {
            Long adminId = Long.parseLong(adminIdField.getText());
            Optional<Administrador> adminOpt = administradorRepository.findById(adminId);

            if (adminOpt.isEmpty()) {
                mostrarError("El Administrador con ID " + adminId + " no existe. Crea uno primero en la base de datos.");
                return;
            }

            Servidor nuevoServidor = new Servidor();
            nuevoServidor.setDireccionIp(ipField.getText());
            nuevoServidor.setSistemaOperativo(osField.getText());
            nuevoServidor.setEstado(statusCombo.getValue());
            nuevoServidor.setAdministrador(adminOpt.get());

            servidorService.guardarServidor(nuevoServidor);
            cerrarVentana();

        } catch (Exception e) {
            mostrarError("Por favor, revisa que todos los campos estén correctos.");
        }
    }

    @FXML
    private void handleCancel() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) ipField.getScene().getWindow();
        stage.close();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error al guardar");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}