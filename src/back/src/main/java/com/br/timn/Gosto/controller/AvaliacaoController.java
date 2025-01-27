package com.br.timn.Gosto.controller;


import com.br.timn.Gosto.exception.ValidacaoException;
import com.br.timn.Gosto.model.Avaliacao;
import com.br.timn.Gosto.model.Imagem;
import com.br.timn.Gosto.model.get.GetAvaliacao;
import com.br.timn.Gosto.model.update.UpdateAvaliacao;
import com.br.timn.Gosto.service.interfaces.crud.AvaliacaoServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ImagemNaAvaliacao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4321")
@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

  @Autowired
  private AvaliacaoServiceCrud avaliacaoServiceCrud;

  @Autowired
  private ImagemNaAvaliacao imagemNaAvaliacao;

  @PostMapping(value = "/cadastro", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> criarAvaliacao(@RequestBody Map<String, Object> dados) {
    try {
      Avaliacao avaliacaoSalva = avaliacaoServiceCrud.createAvaliacao(dados);
      return new ResponseEntity<>(avaliacaoSalva, HttpStatus.CREATED);
    } catch (RuntimeException | ValidacaoException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/{idAvaliacao}", produces = "application/json")
  public ResponseEntity<?> getAvaliacaoById(@PathVariable Long idAvaliacao) {
    try {
      GetAvaliacao avaliacao = avaliacaoServiceCrud.getAvaliacaoById(idAvaliacao);
      return new ResponseEntity<>(avaliacao, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/", produces = "application/json")
  public ResponseEntity<?> getAllAvaliacao() {
    try {
      List<GetAvaliacao> avaliacaos = avaliacaoServiceCrud.getAllAvaliacoes();
      return new ResponseEntity<>(avaliacaos, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> deleteAvaliacao(@PathVariable Long id) {
    try {
      avaliacaoServiceCrud.deleteAvaliacao(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> atualizarAvaliacao(
      @PathVariable Long id, @RequestBody UpdateAvaliacao update) {
    try {
      update.setId(
          id);
      Avaliacao avaliacaoAtualizado = avaliacaoServiceCrud.updateAvaliacao(update);
      return new ResponseEntity<>(avaliacaoAtualizado, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @PostMapping(value = "/{avaliacaoId}/imagens")
  public ResponseEntity<?> addImagem(
      @PathVariable Long avaliacaoId, @RequestBody Map<String, String> requestBody) {
    try {
      String imagemBase64 = requestBody.get("imagem");
      imagemNaAvaliacao.addImagemAvaliacao(avaliacaoId, imagemBase64);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @DeleteMapping(value = "/{avaliacaoId}/imagens/{imagemId}")
  public ResponseEntity<?> removeImagem(
      @PathVariable Long avaliacaoId, @PathVariable Long imagemId) {

    try {
      imagemNaAvaliacao.removeImagem(avaliacaoId, imagemId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/{avaliacaoId}/imagens", produces = "application/json")
  public ResponseEntity<?> getImagens(@PathVariable Long avaliacaoId) {
    try {
      List<Imagem> imagens = imagemNaAvaliacao.getImagens(avaliacaoId);
      return new ResponseEntity<>(imagens, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }
}
