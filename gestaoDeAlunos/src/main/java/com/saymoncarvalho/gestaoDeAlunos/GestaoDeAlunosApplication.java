package com.saymoncarvalho.gestaoDeAlunos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestaoDeAlunosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoDeAlunosApplication.class, args);
	}

}
