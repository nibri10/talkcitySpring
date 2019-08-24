package com.talkcity.talkcity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableJpaAuditing
@SpringBootApplication
public class TalkcityApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalkcityApplication.class, args);
	}

	@RequestMapping("/home")
	public String hello(){return "Hello API TALK CITY";}
}
