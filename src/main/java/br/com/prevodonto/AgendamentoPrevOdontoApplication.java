package br.com.prevodonto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.prevodonto.domain.Atendimento;
import br.com.prevodonto.domain.Cliente;
import br.com.prevodonto.domain.Dentista;
import br.com.prevodonto.domain.enums.EstadoAtendimento;
import br.com.prevodonto.repository.AtendimentoRepository;
import br.com.prevodonto.repository.ClienteRepository;
import br.com.prevodonto.repository.DentistaRepository;

@SpringBootApplication
public class AgendamentoPrevOdontoApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private DentistaRepository dentistaRepository;

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoPrevOdontoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cliente c1 = new Cliente(null, "Igor", "12843335663", LocalDate.now());
		Cliente c2 = new Cliente(null, "Ian", "12843335662", LocalDate.now());

		Dentista d1 = new Dentista(null, "Igor", "nada demais", "sei la");
		Dentista d2 = new Dentista(null, "Igor2", "nada demais2", "sei la2");

		d1.getCliente().addAll(Arrays.asList(c1));
		d2.getCliente().addAll(Arrays.asList(c2));

		c1.getDentista().addAll(Arrays.asList(d1));
		c2.getDentista().addAll(Arrays.asList(d2));

		dentistaRepository.saveAll(Arrays.asList(d1, d2));
		clienteRepository.saveAll(Arrays.asList(c1, c2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Atendimento a1 = new Atendimento(null, LocalDateTime.of(2020, 9, 21, 10, 10, 32), "Obturação", c1, d2,EstadoAtendimento.ATENDIDO);
		Atendimento a2 = new Atendimento(null, LocalDateTime.of(2020, 9, 21, 10, 10), "Obturação", c1, d1,EstadoAtendimento.ATRASADO);
		Atendimento a3 = new Atendimento(null, LocalDateTime.of(2020, 9, 21, 10, 10), "Obturação", c2, d2,EstadoAtendimento.CANCELADO);
		Atendimento a4 = new Atendimento(null, LocalDateTime.of(2020, 9, 21, 10, 10), "Obturação", c2, d1,EstadoAtendimento.PENDENTE);
		atendimentoRepository.saveAll(Arrays.asList(a1, a2, a3, a4));
	}
}
