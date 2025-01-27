package com.br.timn.Gosto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.timn.Gosto.model.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
  public Optional<Avaliacao> findById(Long idAvaliacao); 
}
