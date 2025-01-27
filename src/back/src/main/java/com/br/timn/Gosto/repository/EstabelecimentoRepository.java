package com.br.timn.Gosto.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.timn.Gosto.model.Estabelecimento;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
  public Optional<Estabelecimento> findByEmailEstabelecimento(String emailEstabelecimento); 

  public Optional<Long> findIdByEmailEstabelecimento(String emailEstabelecimento); 
}
