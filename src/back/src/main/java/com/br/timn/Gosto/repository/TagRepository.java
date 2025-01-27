package com.br.timn.Gosto.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.timn.Gosto.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
  public Optional<Tag> findByNomeTag(String nomeTag);

  public Optional<Long> findIdByNomeTag(String nomeTag); 
}
