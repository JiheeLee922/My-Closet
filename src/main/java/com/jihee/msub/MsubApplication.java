package com.jihee.msub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing	//AuditingEntityListener.class 사용하려고
@SpringBootApplication
public class MsubApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsubApplication.class, args);
	}

}
