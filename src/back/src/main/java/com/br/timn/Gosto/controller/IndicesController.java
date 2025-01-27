package com.br.timn.Gosto.controller;

import com.br.timn.Gosto.service.interfaces.crud.IndicesGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4321")
@RestController
@RequestMapping("/indices")
public class IndicesController {

  @Autowired
  private IndicesGet indicesGet;

  @GetMapping(value = "/{idEstabelecimento}", produces = "application/json")
  public ResponseEntity<?> calcularTodosIndices(@PathVariable Long idEstabelecimento) {
    try {
      Map<String, Object> resultadosIndices = indicesGet.calcularTodosIndices(idEstabelecimento);
      return new ResponseEntity<>(resultadosIndices, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/{idEstabelecimento}/{indiceNome}", produces = "application/json")
  public ResponseEntity<?> calcularIndiceEspecifico(
      @PathVariable Long idEstabelecimento,
      @PathVariable String indiceNome) {
    try {
      Object resultadoIndice = indicesGet.calcularIndiceEspecifico(idEstabelecimento, indiceNome);
      if (resultadoIndice == null) {
        return new ResponseEntity<>(resultadoIndice, HttpStatus.NOT_FOUND);
      }
      return ResponseEntity.ok(resultadoIndice);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }
}
