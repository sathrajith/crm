package com.crm.crm_web_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrmWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmWebAppApplication.class, args);
	}

}
