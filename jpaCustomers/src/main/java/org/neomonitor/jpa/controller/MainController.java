package org.neomonitor.jpa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.neomonitor.jpa.entity.Servidor;
import org.neomonitor.jpa.service.ServidorService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MainController {

    @FXML private TableView<Servidor> servidorTable;
    @FXML private TableColumn<Servidor, String> colIp;
    @FXML private TableColumn<Servidor, String> colSo;
    @FXML private TableColumn<Servidor, String> colEstado;
    @FXML private TableColumn<Servidor, String> colUbicacion;
    @FXML private TextField filterField;

    private final ServidorService servidorService;
    private final ApplicationContext applicationContext;
    private ObservableList<Servidor> servidorData = FXCollections.observableArrayList();

    public MainController(ServidorService servidorService, ApplicationContext applicationContext) {
        this.servidorService = servidorService;
        this.applicationContext = applicationContext;
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
            mostrarMensaje("Éxito", "Servidor eliminado correctamente.", Alert.AlertType.INFORMATION);
        } else {
            mostrarMensaje("Error", "Por favor, selecciona un servidor de la lista.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void openAddForm() {
        abrirNuevaVentana("/view/server-form-view.fxml", "Añadir Servidor");
    }

    @FXML
    private void viewAlerts() {
        abrirNuevaVentana("/view/alertas-view.fxml", "Historial de Alertas");
    }

    private void abrirNuevaVentana(String fxmlPath, String titulo) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            // Crucial: Le decimos a JavaFX que Spring instancie el controlador
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.showAndWait(); // Pausa la ejecución hasta que se cierre la ventana nueva

            // Recargamos los datos de la tabla por si hemos añadido un servidor nuevo
            loadData();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", "No se pudo abrir la ventana.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarMensaje(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}