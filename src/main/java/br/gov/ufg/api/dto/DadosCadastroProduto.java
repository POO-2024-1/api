package br.gov.ufg.api.dto;

import java.math.BigDecimal;

public record DadosCadastroProduto(Integer id, String nome, String descricao, BigDecimal preco,int estoque, String imagem) {



}
