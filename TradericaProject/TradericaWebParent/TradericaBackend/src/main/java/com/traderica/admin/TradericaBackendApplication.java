package com.traderica.admin;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.traderica.common.entity", "com.traderica.admin.user"})
public class TradericaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradericaBackendApplication.class, args);
	}

}
