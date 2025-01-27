package com.br.timn.Gosto.controller;

import com.br.timn.Gosto.model.Mesa;
import com.br.timn.Gosto.model.get.GetMesa;
import com.br.timn.Gosto.model.get.GetReserva;
import com.br.timn.Gosto.model.update.UpdateMesa;
import com.br.timn.Gosto.service.interfaces.crud.MesaServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ReservaMesa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4321")
@RestController
@RequestMapping("/mesa")
public class MesaController {

  @Autowired
  private MesaServiceCrud mesaServiceCrud;

  @Autowired
  private ReservaMesa reservaMesaService;

  @PostMapping(value = "/cadastro", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> criarMesa(@RequestBody Map<String, Object> dados) {
    try {
      Mesa mesaSalva = mesaServiceCrud.createMesa(dados);
      return new ResponseEntity<>(mesaSalva, HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/{idMesa}", produces = "application/json")
  public ResponseEntity<?> getMesaById(@PathVariable Long idMesa) {
    try {
      GetMesa mesa = mesaServiceCrud.getMesaById(idMesa);
      return new ResponseEntity<>(mesa, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/", produces = "application/json")
  public ResponseEntity<?> getAllMesas() {
    try {
      List<GetMesa> mesas = mesaServiceCrud.getAllMesas();
      return new ResponseEntity<>(mesas, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> deleteMesa(@PathVariable Long id) {
    try {
      mesaServiceCrud.deleteMesa(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> atualizarMesa(@PathVariable Long id, @RequestBody UpdateMesa update) {
    try {
      update.setId(id);
      Mesa mesaAtualizada = mesaServiceCrud.updateMesa(update);
      return new ResponseEntity<>(mesaAtualizada, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @PostMapping(value = "/{idMesa}/reservas/{idReserva}", produces = "application/json")
  public ResponseEntity<?> adicionarReserva(
      @PathVariable Long idMesa,
      @PathVariable Long idReserva) {
    try {
      reservaMesaService.addReservaMesa(idMesa, idReserva);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @DeleteMapping(value = "/{idMesa}/reservas/{idReserva}")
  public ResponseEntity<?> removerReserva(
      @PathVariable Long idMesa,
      @PathVariable Long idReserva) {
    try {
      reservaMesaService.removeReserva(idMesa, idReserva);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/{idMesa}/reservas", produces = "application/json")
  public ResponseEntity<?> listarReservas(@PathVariable Long idMesa) {
    try {
      List<GetReserva> reservas = reservaMesaService.getReservas(idMesa);
      return new ResponseEntity<>(reservas, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }
}
