package br.com.challenge.votingsessionmanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "Voting Session Manager", version = "1.0", description = "API Documentation v1.0"))
public class VotingSessionManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingSessionManagerApplication.class, args);
	}

}
