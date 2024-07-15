package br.gov.ufg.api.controller;

import br.gov.ufg.api.dto.DadosCadastroProduto;
import br.gov.ufg.api.dto.ProdutoDTO;
import br.gov.ufg.api.entity.Produto;
import br.gov.ufg.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/ecommerce-api/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

//    @PostMapping
//    public void cadastrar(@RequestBody DadosCadastroProduto dados){
//        System.out.println(dados);
//    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody DadosCadastroProduto dados) {
        try {
            // Aqui você pode adicionar lógica para validar os dados recebidos, se necessário

            // Exemplo de validação básica
            if (dados == null) {
                return ResponseEntity.badRequest().body("Dados de cadastro não fornecidos");
            }
            // Realizar o cadastro
            repository.save(new Produto(dados));

            return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar o produto: " + e.getMessage());
        }
    }

    @GetMapping("/listar-arquivo")
    public ResponseEntity<?> listarProduto() {
        try {
            List<Produto> produtos = ProdutoDTO.lerProdutosDoArquivo();
            return ResponseEntity.ok(produtos);
        } catch (URISyntaxException | IOException e) {
            System.out.println("Erro ao tentar ler produtos do arquivo: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar ler produtos do arquivo");
        }
    }

    @GetMapping("/listar-bancoDeDados")
    public ResponseEntity<List<Produto>> listarProdutoBancoDeDados() {
        try {
            List<Produto> produtos = repository.findAll();
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        } catch (Exception e) {
            // Em caso de erro, captura a exceção e retorna uma resposta de erro
            System.out.println("Erro ao listar produtos do banco de dados: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/idBancoDeDados/{id}")
    public ResponseEntity<Produto> obterProdutoBancoDeDadosPorId(@PathVariable Integer id) {
        Optional<Produto> produto = repository.findById(id);
        return produto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Produto> obterProdutoPorId(@PathVariable Integer id) {
        try {
            List<Produto> produtos = ProdutoDTO.lerProdutosDoArquivo();

            Produto produto = produtos.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (produto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else return new ResponseEntity<>(produto, HttpStatus.OK);

        } catch (URISyntaxException | IOException e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
