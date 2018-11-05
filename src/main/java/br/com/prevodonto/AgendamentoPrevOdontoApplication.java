package br.com.prevodonto;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.prevodonto.domain.Cliente;
import br.com.prevodonto.domain.Dentista;
import br.com.prevodonto.repository.ClienteRepository;
import br.com.prevodonto.repository.DentistaRepository;

@SpringBootApplication
public class AgendamentoPrevOdontoApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private DentistaRepository dentistaRepository;

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoPrevOdontoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cliente c1 = new Cliente(null, "Igor", "12843335663", LocalDate.now());
		Cliente c2 = new Cliente(null, "Ian", "12843335663", LocalDate.now());

		Dentista d1 = new Dentista(null, "Igor", "nada demais", "sei la");
		Dentista d2 = new Dentista(null, "Igor2", "nada demais2", "sei la2");

		d1.getClientes().addAll(Arrays.asList(c1));
		d2.getClientes().addAll(Arrays.asList(c2));

		c1.getDentistas().addAll(Arrays.asList(d1));
		c2.getDentistas().addAll(Arrays.asList(d2));

		dentistaRepository.saveAll(Arrays.asList(d1, d2));
		clienteRepository.saveAll(Arrays.asList(c1, c2));
	}
}
