package br.gov.ufg.dto;

import br.gov.ufg.entity.Cliente;
import br.gov.ufg.entity.ClientePessoaFisica;
import br.gov.ufg.entity.ClientePessoaJuridica;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date; // Importação correta
import java.util.List;
import java.util.stream.Collectors;

public class ClienteDTO {

    private static final String CAMINHO_ARQUIVO = "database/clientes.txt";

    // Método para ler clientes do arquivo
    public static List<Cliente> lerClientesDoArquivo() throws URISyntaxException, IOException {

        // Tentar obter o caminho do arquivo como um recurso
        java.net.URL resource = ClienteDTO.class.getClassLoader().getResource(CAMINHO_ARQUIVO);

        // Converter URL para URI e obter o caminho absoluto
        java.nio.file.Path caminhoArquivoAbsoluto = Paths.get(resource.toURI());

        if (resource != null) {
            return Files.lines(caminhoArquivoAbsoluto)
                    .map(line -> {
                        String[] cliente = line.split(",");

                        // Instanciar a classe concreta que herda de Cliente com base no tipo (PF ou PJ)
                        if ("PF".equals(cliente[0])) {
                            return new ClientePessoaFisica(
                                    Integer.parseInt(cliente[1]), // IdCliente
                                    cliente[2],                   // nome
                                    cliente[3],                   // email
                                    cliente[4],                   // endereco
                                    cliente[5],                   // telefone
                                    cliente[6],                   // userName
                                    cliente[7],                   // senha
                                    cliente[8],                   // cpf
                                    cliente[9],                   // rg
                                    (Data) new Date(Long.parseLong(cliente[10])) // data
                            );
                        } else if ("PJ".equals(cliente[0])) {
                            return new ClientePessoaJuridica(
                                    Integer.parseInt(cliente[1]), // IdCliente
                                    cliente[2],                   // nome
                                    cliente[3],                   // email
                                    cliente[4],                   // endereco
                                    cliente[5],                   // telefone
                                    cliente[6],                   // userName
                                    cliente[7],                   // senha
                                    cliente[8],                   // documento
                                    cliente[9],                   // inscricaoEstadual
                                    cliente[10],                  // razaoSocial
                                    cliente[11]                   // tipoDocumento
                            );
                        } else {
                            return null;
                        }
                    })
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
