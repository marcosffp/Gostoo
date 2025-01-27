package com.br.timn.Gosto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.timn.Gosto.model.Avaliador;
import java.util.Optional;

@Repository
public interface AvaliadorRepository extends JpaRepository<Avaliador, Long> {
  public Optional<Avaliador> findByEmailAvaliador(String emailAvaliador);

  public Optional<Long> findIdByEmailAvaliador(String emailAvaliador); 
}
