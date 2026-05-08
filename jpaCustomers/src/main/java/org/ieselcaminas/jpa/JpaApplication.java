package org.ieselcaminas.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
	//En este método definimos nuestro propio código
	@Override
	public void run(String... args) {
		
	}
}
