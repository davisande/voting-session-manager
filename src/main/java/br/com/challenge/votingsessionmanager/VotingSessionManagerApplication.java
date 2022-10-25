package br.com.challenge.votingsessionmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class VotingSessionManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingSessionManagerApplication.class, args);
	}

}
