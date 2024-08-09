package br.gov.ufg.dto;

import br.gov.ufg.api.Main;
import br.gov.ufg.entity.Pedido;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDTO {

    private static final String CAMINHO_ARQUIVO = "database/pedidos.txt";

    // Método para ler pedidos do arquivo
    public static List<Pedido> lerPedidosDoArquivo(String caminhoArquivo) throws IOException, URISyntaxException {

        // Tentar obter o caminho do arquivo como um recurso
        java.net.URL resource = Main.class.getClassLoader().getResource(CAMINHO_ARQUIVO);

        // Converter URL para URI e obter o caminho absoluto
        java.nio.file.Path caminhoArquivoAbsoluto = Paths.get(resource.toURI());

        if (resource != null) {

            return Files.lines(caminhoArquivoAbsoluto)
                    .map(line -> {
                        String[] pedido = line.split(",");
                        return new Pedido(
                                Integer.parseInt(pedido[0]),                       // ID do pedido
                                new Date(Long.parseLong(pedido[1])),              // Data do pedido
                                pedido[2],                                        // Status
                                Integer.parseInt(pedido[3])                       // ID do cliente
                        );
                    })
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
