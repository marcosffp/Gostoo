package com.br.timn.Gosto.controller;

import com.br.timn.Gosto.exception.ReservaNotFoundException;
import com.br.timn.Gosto.model.Reserva;
import com.br.timn.Gosto.model.get.GetReserva;
import com.br.timn.Gosto.model.update.UpdateReserva;
import com.br.timn.Gosto.service.interfaces.crud.ReservaServiceCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4321")
@RestController
@RequestMapping("/reserva")
public class ReservaController {

  @Autowired
  private ReservaServiceCrud reservaServiceCrud;

  @PostMapping(value = "/cadastro", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> criarReserva(@RequestBody Map<String, Object> dados) {
    try {
      Reserva reservaSalva = reservaServiceCrud.createReserva(dados);
      return new ResponseEntity<>(reservaSalva, HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/{idReserva}", produces = "application/json")
  public ResponseEntity<?> getReservaById(@PathVariable Long idReserva) throws ReservaNotFoundException {
    try {
      GetReserva reserva = reservaServiceCrud.getReservaById(idReserva);
      return new ResponseEntity<>(reserva, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/", produces = "application/json")
  public ResponseEntity<?> getAllReservas() {
    try {
      List<GetReserva> reservas = reservaServiceCrud.getAllReservas();
      return new ResponseEntity<>(reservas, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> atualizarReserva(@PathVariable Long id, @RequestBody UpdateReserva update) {
    try {
      update.setId(id);
      Reserva reservaAtualizada = reservaServiceCrud.updateReserva(update);
      return new ResponseEntity<>(reservaAtualizada, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @PutMapping(value = "/{idReserva}/rejeitar", produces = "application/json")
  public ResponseEntity<?> rejeitarReserva(@PathVariable Long idReserva) {
    try {
      reservaServiceCrud.rejeitarReserva(idReserva);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @PutMapping(value = "/{idReserva}/aprovar", produces = "application/json")
  public ResponseEntity<?> aprovarReserva(@PathVariable Long idReserva) {
    try {
      reservaServiceCrud.aprovarReserva(idReserva);
      return ResponseEntity.ok("Reserva aprovada com sucesso!");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }
}
