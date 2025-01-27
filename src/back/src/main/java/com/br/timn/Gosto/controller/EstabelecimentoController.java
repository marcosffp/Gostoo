package com.br.timn.Gosto.controller;


import com.br.timn.Gosto.exception.EstabelecimentoNotFoundException;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Imagem;
import com.br.timn.Gosto.model.Tag;
import com.br.timn.Gosto.model.get.GetAvaliacaoEstabelecimento;
import com.br.timn.Gosto.model.get.GetMesaEstabelecimento;
import com.br.timn.Gosto.model.get.GetReservaEstabelecimento;
import com.br.timn.Gosto.model.update.UpdateEstabelecimento;
import com.br.timn.Gosto.security.JwtResponse;
import com.br.timn.Gosto.service.interfaces.crud.EstabelecimentoServiceCrud;
import com.br.timn.Gosto.service.interfaces.gerenciamento.AvaliacaoEstabelecimento;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ImagemNoEstabelecimento;
import com.br.timn.Gosto.service.interfaces.gerenciamento.MesaEstabelecimento;
import com.br.timn.Gosto.service.interfaces.gerenciamento.ReservaEstabelecimento;
import com.br.timn.Gosto.service.interfaces.gerenciamento.TagNoEstabelecimento;
import com.br.timn.Gosto.service.interfaces.security.GerarTokenParaEstabelecimento;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4321")
@RestController
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {

  @Autowired
  private EstabelecimentoServiceCrud estabelecimentoServiceCrud;

  @Autowired
  private TagNoEstabelecimento tagNoEstabelecimento;

  @Autowired
  private GerarTokenParaEstabelecimento gerarTokenParaEstabelecimento;

  @Autowired
  private ImagemNoEstabelecimento imagemNoEstabelecimento;

  @Autowired
  private AvaliacaoEstabelecimento avaliacaoEstabelecimento;

  @Autowired
  private MesaEstabelecimento mesaEstabelecimento;

  @Autowired
  private ReservaEstabelecimento reservaEstabelecimento;

  @PostMapping(value = "/cadastro", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> criarEstabelecimento(
      @RequestBody Estabelecimento estabelecimento) {
    try {
      Estabelecimento novoEstabelecimento = estabelecimentoServiceCrud.createEstabelecimento(estabelecimento);
      String token = gerarTokenParaEstabelecimento.gerarTokenParaEstabelecimento(
          novoEstabelecimento, novoEstabelecimento.getIdEstabelecimento());
      return new ResponseEntity<>(new JwtResponse(token), HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/{email}", produces = "application/json")
  public ResponseEntity<?> getEstabelecimentoByEmail(@PathVariable String email) {
    try {
      Estabelecimento estabelecimento = estabelecimentoServiceCrud.getEstabelecimentoByEmail(email);
      return new ResponseEntity<>(estabelecimento, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/", produces = "application/json")
  public ResponseEntity<?> getAllEstabelecimentos() {
    try {
      List<Estabelecimento> estabelecimentos = estabelecimentoServiceCrud.getAllEstabelecimentos();
      return new ResponseEntity<>(estabelecimentos, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> deleteEstabelecimento(@PathVariable Long id) {
    try {
      estabelecimentoServiceCrud.deleteEstabelecimento(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> atualizarEstabelecimento(
      @PathVariable Long id, @RequestBody UpdateEstabelecimento update) {
    try {
      update.setId(
          id);
      Estabelecimento estabelecimentoAtualizado = estabelecimentoServiceCrud.updateEstabelecimento(update);
      return new ResponseEntity<>(estabelecimentoAtualizado, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @PostMapping(value = "/{estabelecimentoId}/tags/{nomeTag}")
  public ResponseEntity<?> addTag(
      @PathVariable Long estabelecimentoId, @PathVariable String nomeTag) {
    try {
      tagNoEstabelecimento.addTag(estabelecimentoId, nomeTag);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @DeleteMapping(value = "/{estabelecimentoId}/tags/{nomeTag}")
  public ResponseEntity<?> removeTag(
      @PathVariable Long estabelecimentoId, @PathVariable String nomeTag) {
    try {
      tagNoEstabelecimento.removeTag(estabelecimentoId, nomeTag);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/{estabelecimentoId}/tags", produces = "application/json")
  public ResponseEntity<?> getTags(@PathVariable Long estabelecimentoId) {
    try {
      List<Tag> tags = tagNoEstabelecimento.getTags(estabelecimentoId);
      return new ResponseEntity<>(tags, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @PostMapping(value = "/{estabelecimentoId}/imagens")
  public ResponseEntity<?> addImagemOutro(
      @PathVariable Long estabelecimentoId, @RequestBody Map<String, String> requestBody) {
    try {
      String imagemBase64 = requestBody.get("imagem");
      imagemNoEstabelecimento.addImagemOutro(estabelecimentoId, imagemBase64);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @DeleteMapping(value = "/{estabelecimentoId}/imagens/{imagemId}")
  public ResponseEntity<?> removeImagem(
      @PathVariable Long estabelecimentoId, @PathVariable Long imagemId) {
    try {
      imagemNoEstabelecimento.removeImagem(estabelecimentoId, imagemId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/{estabelecimentoId}/imagens", produces = "application/json")
  public ResponseEntity<?> getImagens(@PathVariable Long estabelecimentoId) {
    try {
      List<Imagem> imagens = imagemNoEstabelecimento.getImagens(estabelecimentoId);
      return new ResponseEntity<>(imagens, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/{estabelecimentoId}/avaliacoes", produces = "application/json")
  public ResponseEntity<?> getAvaliacoes(@PathVariable Long estabelecimentoId)
      throws EstabelecimentoNotFoundException {
    try {
      List<GetAvaliacaoEstabelecimento> avaliacoes = avaliacaoEstabelecimento.getAllAvaliacoes(estabelecimentoId);
      return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping(value = "/{estabelecimentoId}/mesas", produces = "application/json")
  public ResponseEntity<?> getMesas(@PathVariable Long estabelecimentoId) {
    try {
      List<GetMesaEstabelecimento> mesas = mesaEstabelecimento.getAllMesa(estabelecimentoId);
      return new ResponseEntity<>(mesas, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }

  @GetMapping("/{estabelecimentoId}/reservas")
  public ResponseEntity<?> getReservasEstabelecimento(
      @PathVariable Long estabelecimentoId) {
    try {
      List<GetReservaEstabelecimento> reservas = reservaEstabelecimento.getReservasEstabelecimento(estabelecimentoId);
      return new ResponseEntity<>(reservas, HttpStatus.OK);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
    }
  }
}
