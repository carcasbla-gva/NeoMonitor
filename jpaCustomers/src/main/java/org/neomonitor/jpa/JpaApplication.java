package org.neomonitor.jpa;

import javafx.scene.image.Image;
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
		springContext = SpringApplication.run(JpaApplication.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/main-view.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		Parent root = fxmlLoader.load();

		primaryStage.setTitle("NeoMonitor - Dashboard");
		primaryStage.setScene(new Scene(root, 800, 600));

		//
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/Logo.png")));

		primaryStage.show();
	}

	@Override
	public void stop() {
		springContext.close();
	}

	@org.springframework.context.annotation.Bean
	public org.springframework.boot.CommandLineRunner initData(org.neomonitor.jpa.repository.AdministradorRepository repo) {
		return args -> {
			if (repo.count() == 0) {
				org.neomonitor.jpa.entity.Administrador admin = new org.neomonitor.jpa.entity.Administrador();
				admin.setNombre("Carlos");
				admin.setEmail("admin@neomonitor.local");
				admin.setRol("SysAdmin");
				repo.save(admin);
				System.out.println("Administrador por defecto creado con ID 1.");
			}
		};
	}

	public static void main(String[] args) {
		launch(JpaApplication.class, args);
	}
}
