package com.br.timn.Gosto.service.interfaces.gerenciamento;

import com.br.timn.Gosto.exception.ValidacaoException;
import com.br.timn.Gosto.model.Estabelecimento;
import com.br.timn.Gosto.model.Imagem;
import java.io.IOException;
import java.util.List;

public interface ImageUpdateEstabelecimento {
        public Imagem updateImagemPerfil(Estabelecimento estabelecimento, byte[] imagemPerfil, String novaimagemPadrao) throws ValidacaoException, IOException;

        public Imagem updateImagemBanner(Estabelecimento estabelecimento, byte[] imagemBanner,
                        String novaimagemPadrao) throws IOException;

        public List<Imagem> updateImagensOutro(Estabelecimento estabelecimento, List<byte[]> imagensOutro,
                        String nome) throws ValidacaoException, IOException;
}
