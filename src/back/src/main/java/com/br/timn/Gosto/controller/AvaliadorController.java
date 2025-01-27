package com.br.timn.Gosto.controller;

import com.br.timn.Gosto.exception.ValidacaoException;
import com.br.timn.Gosto.model.Avaliador;
import com.br.timn.Gosto.model.get.GetAvaliacaoAvaliador;
import com.br.timn.Gosto.model.get.GetReservaAvaliador;
import com.br.timn.Gosto.model.update.UpdateAvaliador;
import com.br.timn.Gosto.security.JwtResponse;
import com.br.timn.Gosto.service.interfaces.crud.AvaliadorServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.AvaliacaoAvaliador;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ReservaAvaliador;
import com.br.timn.Gosto.service.interfaces.security.GerarTokenParaAvaliador;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4321")
@RestController
@RequestMapping("/avaliador")
public class AvaliadorController {

  @Autowired
  private AvaliadorServiceCrud avaliadorCrudService;

  @Autowired
  private GerarTokenParaAvaliador gerarTokenParaAvaliador;

  @Autowired
  private AvaliacaoAvaliador avaliacaoAvaliador;

  @Autowired
  private ReservaAvaliador reservaAvaliador;

  @PostMapping(value = "/cadastro", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> criarAvaliador(@RequestBody Avaliador avaliador) {
    try {
      Avaliador novoAvaliador = avaliadorCrudService.createAvaliador(avaliador);
      String token = gerarTokenParaAvaliador.gerarTokenParaAvaliador(novoAvaliador, novoAvaliador.getIdAvaliador());
      return new ResponseEntity<>(new JwtResponse(token), HttpStatus.CREATED);
    } catch (ValidacaoException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/{avaliadorEmail}", produces = "application/json")
  public ResponseEntity<?> getAvaliador(@PathVariable String avaliadorEmail) {
    try {
      Avaliador avaliador = avaliadorCrudService.getAvaliadorByEmail(avaliadorEmail);
      return new ResponseEntity<>(avaliador, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @DeleteMapping(value = "/{avaliadorId}")
  public ResponseEntity<?> deleteAvaliador(@PathVariable Long avaliadorId) {
    try {
      avaliadorCrudService.deleteAvaliador(avaliadorId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> atualizarAvaliador(@RequestBody UpdateAvaliador update) {
    try {
      Avaliador avaliadorAtualizado = avaliadorCrudService.updateAvaliador(update);
      return new ResponseEntity<>(avaliadorAtualizado, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/{avaliadorId}/avaliacoes", produces = "application/json")
  public ResponseEntity<?> getAvaliacoes(@PathVariable Long avaliadorId) {
    try {
      List<GetAvaliacaoAvaliador> avaliacoes = avaliacaoAvaliador.getAllAvaliacoes(avaliadorId);
      return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping("/{avaliadorId}/reservas")
  public ResponseEntity<?> getReservasAvaliador(@PathVariable Long avaliadorId) {
    try {
      List<GetReservaAvaliador> reservas = reservaAvaliador.getReservasAvaliador(avaliadorId);
      return new ResponseEntity<>(reservas, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }
}
