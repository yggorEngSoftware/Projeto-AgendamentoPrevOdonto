package br.com.prevodonto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.prevodonto.service.impl.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	@Autowired
	private DBService dbService;

	@Bean
	public boolean instantiateDatabase() {
		dbService.instantiateTestDatabase();
		return true;
	}
}
