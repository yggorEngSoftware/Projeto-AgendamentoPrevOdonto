package br.com.prevodonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prevodonto.domain.Dentista;

public interface DentistaRepository extends JpaRepository<Dentista, Integer> {

}
