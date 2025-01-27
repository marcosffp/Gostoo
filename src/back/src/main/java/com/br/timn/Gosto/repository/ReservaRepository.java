package com.br.timn.Gosto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.timn.Gosto.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    public Optional<Reserva> findById(Long idReserva); 
}
