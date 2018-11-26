package br.com.prevodonto.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prevodonto.domain.Atendimento;
import br.com.prevodonto.domain.Cliente;
import br.com.prevodonto.domain.Dentista;
import br.com.prevodonto.domain.enums.EstadoAtendimento;
import br.com.prevodonto.repository.AtendimentoRepository;
import br.com.prevodonto.repository.ClienteRepository;
import br.com.prevodonto.repository.DentistaRepository;	

@Service
public class DBService {
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private DentistaRepository dentistaRepository;

	@Autowired
	private AtendimentoRepository atendimentoRepository;

public void instantiateTestDatabase() {
	Dentista d1 = new Dentista(null, "Larissa Chagas", "12843335663", "Ortodontia");
	Dentista d2 = new Dentista(null, "Rafael Meideiros", "96844582668", "Proteses");

	Cliente c1 = new Cliente(null, "Antônio Marcos Ferreira", "12843335663", LocalDate.now());
	Cliente c2 = new Cliente(null, "José da Silva", "55150624691", LocalDate.now());
	Cliente c3 = new Cliente(null, "Marcelo Andrade Silva", "55150624691", LocalDate.now());
	Cliente c4 = new Cliente(null, "Maria Tereza Januário", "55150624691", LocalDate.now());
//	d1.getCliente().addAll(Arrays.asList(c1));
//	d2.getCliente().addAll(Arrays.asList(c2));

//	c1.getDentista().addAll(Arrays.asList(d1));
//	c2.getDentista().addAll(Arrays.asList(d2));

	dentistaRepository.saveAll(Arrays.asList(d1, d2));
	clienteRepository.saveAll(Arrays.asList(c1, c2, c3, c4));

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	Atendimento a1 = new Atendimento(null, LocalDateTime.of(2018, 11, 20, 13, 00), "Obturação", c1, d1,EstadoAtendimento.PENDENTE);
	Atendimento a2 = new Atendimento(null, LocalDateTime.of(2018, 11, 20, 13, 00), "Prótese", c2, d2,EstadoAtendimento.PENDENTE);
	Atendimento a3 = new Atendimento(null, LocalDateTime.of(2018, 11, 20, 13, 30), "Obturação", c3, d1,EstadoAtendimento.PENDENTE);
	Atendimento a4 = new Atendimento(null, LocalDateTime.of(2018, 11, 20, 13, 30), "Canal", c2, d2,EstadoAtendimento.PENDENTE);
	Atendimento a5 = new Atendimento(null, LocalDateTime.of(2018, 11, 20, 14, 00), "Canal", c4, d2,EstadoAtendimento.PENDENTE);
	atendimentoRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5));

	
}

}
