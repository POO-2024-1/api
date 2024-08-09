package br.gov.ufg.controller;

import br.gov.ufg.dto.ClienteDTO;
import br.gov.ufg.entity.Cliente;
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
public class ClienteController {

    @GetMapping("/listarClientes")
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();

        try {
            clientes = ClienteDTO.lerClientesDoArquivo();
        } catch (URISyntaxException | IOException e) {
            System.out.println("Não foi possível abrir o arquivo de clientes: " + e);
            throw new RuntimeException("Não foi possível abrir o arquivo de clientes");
        }

        return clientes;
    }

    @GetMapping("/listarClienteById/{id}")
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable Integer id) {

        List<Cliente> clientes;
        try {
            clientes = ClienteDTO.lerClientesDoArquivo();

        } catch (URISyntaxException | IOException e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Cliente cliente = clientes.stream()
                .filter(cli -> cli.getIdCliente() == id)
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
    }
}
