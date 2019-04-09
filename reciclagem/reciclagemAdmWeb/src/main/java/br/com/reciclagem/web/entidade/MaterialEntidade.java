package br.com.reciclagem.web.entidade;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "material")
public class MaterialEntidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "pk_codigo", nullable = true)
	private int codigo;
	
	private String nome;
	
	private String descricao;
	
	@Column(name = "valor_compra", nullable = true)
	private BigDecimal valorCompra;
	
	@Column(name = "valor_venda", nullable = true)
	private BigDecimal valorVenda;
	
	@Column(name = "unidade_de_medida", nullable = true)
	private String UnidadeMedida;
	
	@Column(name = "quantidade_estoque", nullable = true)
	private BigDecimal quantidadeEmEstoque;
	
	@Column(name = "quantidade_minima_para_venda", nullable = true)
	private BigDecimal quantidadeMinimaParaVenda;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public String getUnidadeMedida() {
		return UnidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		UnidadeMedida = unidadeMedida;
	}

	public BigDecimal getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(BigDecimal quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	public BigDecimal getQuantidadeMinimaParaVenda() {
		return quantidadeMinimaParaVenda;
	}

	public void setQuantidadeMinimaParaVenda(BigDecimal quantidadeMinimaParaVenda) {
		this.quantidadeMinimaParaVenda = quantidadeMinimaParaVenda;
	}
	
}
