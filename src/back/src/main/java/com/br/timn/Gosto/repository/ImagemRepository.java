package com.br.timn.Gosto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.timn.Gosto.model.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {
  public Optional<Imagem> findById(Long idImagem); 
}
