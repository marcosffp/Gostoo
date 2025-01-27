package com.br.timn.Gosto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.timn.Gosto.model.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
  public Optional<Mesa> findById(Long idMesa);
}
