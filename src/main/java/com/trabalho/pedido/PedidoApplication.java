package com.trabalho.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.trabalho")
@EntityScan(basePackages = {"com.trabalho.domains","com.trabalho.domains.enums"})
@EnableJpaRepositories(basePackages = "com.trabalho.repositories")
@SpringBootApplication
public class PedidoApplication {
	public static void main(String[] args) {
		SpringApplication.run(PedidoApplication.class, args);
	}
}
