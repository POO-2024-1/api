package br.gov.ufg.controller;

import br.gov.ufg.dto.PedidoDTO;
import br.gov.ufg.entity.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class PedidoController {

    private static final String CAMINHO_ARQUIVO = "database/pedidos.txt";

    @GetMapping("/listarPedidos")
    public List<Pedido> listar() {
        List<Pedido> pedidos = new ArrayList<>();

        try {
            pedidos = PedidoDTO.lerPedidosDoArquivo(CAMINHO_ARQUIVO);
        } catch (URISyntaxException | IOException e) {
            System.out.println("Não foi possível abrir o arquivo de pedidos: " + e);
            throw new RuntimeException("Não foi possível abrir o arquivo de pedidos");
        }

        return pedidos;
    }

    @GetMapping("/listarPedidoById/{id}")
    public ResponseEntity<Pedido> obterPedidoPorId(@PathVariable Integer id) {
        List<Pedido> pedidos;
        try {
            pedidos = PedidoDTO.lerPedidosDoArquivo(CAMINHO_ARQUIVO);
        } catch (URISyntaxException | IOException e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Pedido pedido = pedidos.stream()
                .filter(ped -> ped.getIdPedido() == id)
                .findFirst()
                .orElse(null);

        if (pedido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        }
    }
}
