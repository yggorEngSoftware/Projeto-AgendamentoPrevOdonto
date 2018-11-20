package br.com.prevodonto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prevodonto.domain.Dentista;
@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {
	
	Optional<Dentista> findByCpf(String cpf);

	Optional<Dentista> findByNome(String nome);

}
