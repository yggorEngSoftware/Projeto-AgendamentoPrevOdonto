package br.com.prevodonto.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prevodonto.domain.Atendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

	Optional<Atendimento> findByDataAtendimento(LocalDateTime dataAtendimento);
	List<Atendimento> findByDentistaCpf(String cpf);
}
