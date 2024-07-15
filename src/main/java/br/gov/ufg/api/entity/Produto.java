package br.gov.ufg.api.entity;

import br.gov.ufg.api.dto.DadosCadastroProduto;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "produto")
@Entity(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "preco")
    private BigDecimal preco;
    @Column(name = "estoque")
    private int estoque;
    @Lob
    @Column(name = "imagem", length = 1048576)
    private byte[] imagem;

    public Produto(DadosCadastroProduto dadosCadastroProduto) {
        this.nome = dadosCadastroProduto.nome();
        this.descricao = dadosCadastroProduto.descricao();
        this.preco = dadosCadastroProduto.preco();
        this.estoque = dadosCadastroProduto.estoque();
        this.imagem = dadosCadastroProduto.imagem().getBytes();
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
