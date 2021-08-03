package com.jihee.msub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MsubApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsubApplication.class, args);
	}

}
