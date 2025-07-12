package com.example.Biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Essa anotação é a junção de:
//@Configuration: indica que esta classe contém configurações de Spring
//@EnableAutoConfiguration: ativa a configuração automática do Spring Boot
//@ComponentScan: faz o Spring "enxergar" todos os componentes do projeto ( controllers, services, etc )
@SpringBootApplication
public class BibliotecaApplication {

	// Método main - ponto de entrada da aplicação Java
	public static void main(String[] args) {
		// Indica a aplicação Spring Boot
		SpringApplication.run(BibliotecaApplication.class, args);
	}

}
