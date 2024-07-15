package br.gov.ufg.api.dados;

import br.gov.ufg.api.entity.Produto;
import br.gov.ufg.api.main.Main;
import br.gov.ufg.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public DataInitializer(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Inserir produtos no banco de dados
        List<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto(1, "Calça Jeans", "calça jeans azul", new BigDecimal("400.00"), 10, lerImagemComoBytes("imagens/calca_jeans.png")));
        produtos.add(new Produto(2, "Blusa", "Blusa bonita", new BigDecimal("300.00"), 11, lerImagemComoBytes("imagens/blusa.png")));
        produtos.add(new Produto(3, "Camiseta", "linda e da cor preta", new BigDecimal("200.00"), 12, lerImagemComoBytes("imagens/camiseta_preta.png")));
        produtos.add(new Produto(4, "Carteira", "carteira diferente", new BigDecimal("100.00"), 13, lerImagemComoBytes("imagens/carteira.png")));
        produtos.add(new Produto(5, "Camiseta Polo", "Camiseta polo infantil", new BigDecimal("500.00"), 15, lerImagemComoBytes("imagens/polo_infantil.png")));
        produtos.add(new Produto(6, "Sapato", "Sapato confortável", new BigDecimal("600.00"), 20, lerImagemComoBytes("imagens/sapato.png")));
        produtos.add(new Produto(7, "Shorts de praia", "Para seu banho ser legal", new BigDecimal("700.00"), 25, lerImagemComoBytes("imagens/shorts_praia.png")));
        produtos.add(new Produto(8, "Crocs", "Crocs rosa", new BigDecimal("800.00"), 30, lerImagemComoBytes("imagens/crocs.jpeg")));


        produtoRepository.saveAll(produtos);

        System.out.println("Inserção de produtos no banco de dados concluída.");
    }

    private byte[] lerImagemComoBytes(String fileName) throws IOException {
        URL imagePath = Main.class.getClassLoader().getResource(fileName);

        if (imagePath == null) {
            throw new IOException("Arquivo não encontrado: " + fileName);
        }

        try {
            Path caminhoArquivoAbsoluto = Paths.get(imagePath.toURI());
            return Files.readAllBytes(caminhoArquivoAbsoluto);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Erro ao ler imagem: " + fileName, e);
        }
    }
}