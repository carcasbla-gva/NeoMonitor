package org.neomonitor.jpa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.neomonitor.jpa.entity.Servidor;
import org.neomonitor.jpa.service.ServidorService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainController {

    @FXML
    private TableView<Servidor> servidorTable;
    @FXML private TableColumn<Servidor, String> colIp;
    @FXML private TableColumn<Servidor, String> colSo;
    @FXML private TableColumn<Servidor, String> colEstado;
    @FXML private TableColumn<Servidor, String> colUbicacion;
    @FXML private TextField filterField;

    private final ServidorService servidorService;
    private ObservableList<Servidor> servidorData = FXCollections.observableArrayList();

    public MainController(ServidorService servidorService) {
        this.servidorService = servidorService;
    }

    @FXML
    public void initialize() {
        colIp.setCellValueFactory(new PropertyValueFactory<>("direccionIp"));
        colSo.setCellValueFactory(new PropertyValueFactory<>("sistemaOperativo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        colUbicacion.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Servidor s = getTableRow().getItem();
                    setText(servidorService.localizarCentroDeDatos(s.getDireccionIp()));
                }
            }
        });

        loadData();
    }

    private void loadData() {
        servidorData.setAll(servidorService.obtenerTodos());
        servidorTable.setItems(servidorData);
    }

    @FXML
    private void handleFilter() {
        String filter = filterField.getText();
        if (filter == null || filter.isEmpty()) {
            loadData();
        } else {
            List<Servidor> filtrados = servidorService.filtrarPorEstado(filter);
            servidorData.setAll(filtrados);
        }
    }

    @FXML
    private void handleDelete() {
        Servidor seleccionado = servidorTable.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            servidorService.eliminarServidor(seleccionado.getId());
            loadData();
            mostrarMensaje("Éxito", "Servidor eliminado correctamente.");
        } else {
            mostrarMensaje("Error", "Por favor, selecciona un servidor de la lista.");
        }
    }

    @FXML private void openAddForm() { System.out.println("Abriendo formulario..."); }
    @FXML private void viewAlerts() { System.out.println("Abriendo alertas..."); }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}