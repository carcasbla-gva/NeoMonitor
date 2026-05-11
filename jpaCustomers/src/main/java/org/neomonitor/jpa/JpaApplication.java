package org.neomonitor.jpa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static javafx.application.Application.launch;

@SpringBootApplication
public class JpaApplication extends Application {

	private ConfigurableApplicationContext springContext;

	@Override
	public void init() throws Exception {
		// Arrancamos el motor de Spring Boot antes de mostrar la ventana
		springContext = SpringApplication.run(JpaApplication.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Importamos y cargamos el archivo de diseño FXML
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/main-view.fxml"));

		// Le decimos a JavaFX que Spring se encargue de crear los controladores
		fxmlLoader.setControllerFactory(springContext::getBean);

		Parent root = fxmlLoader.load();
		primaryStage.setTitle("NeoMonitor - Dashboard");
		primaryStage.setScene(new Scene(root, 800, 600)); // Tamaño de la ventana
		primaryStage.show();
	}

	@Override
	public void stop() {
		// Apagamos Spring cuando el usuario cierra la ventana
		springContext.close();
	}

	public static void main(String[] args) {
		// Lanzamos la aplicación JavaFX
		launch(JpaApplication.class, args);
	}
}
