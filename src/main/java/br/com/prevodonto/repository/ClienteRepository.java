package br.com.prevodonto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prevodonto.domain.Cliente;

/**
 * @author yggor
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	/**
	 * @param cpf
	 * @return
	 */
	Optional<Cliente> findByCpf(String cpf);

	/**
	 * @param nome
	 * @return
	 */
	Optional<Cliente> findByNome(String nome);


	
	
}
