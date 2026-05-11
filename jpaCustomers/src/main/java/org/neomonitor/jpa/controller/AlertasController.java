package org.neomonitor.jpa.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.neomonitor.jpa.entity.Alerta;
import org.neomonitor.jpa.repository.AlertaRepository;
import org.springframework.stereotype.Component;

@Component
public class AlertasController {

    @FXML private TableView<Alerta> alertasTable;
    @FXML private TableColumn<Alerta, String> colFecha;
    @FXML private TableColumn<Alerta, String> colGravedad;
    @FXML private TableColumn<Alerta, String> colDescripcion;

    private final AlertaRepository alertaRepository;

    public AlertasController(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    @FXML
    public void initialize() {
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));
        colGravedad.setCellValueFactory(new PropertyValueFactory<>("nivelGravedad"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        alertasTable.setItems(FXCollections.observableArrayList(alertaRepository.findAll()));
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) alertasTable.getScene().getWindow();
        stage.close();
    }
}
