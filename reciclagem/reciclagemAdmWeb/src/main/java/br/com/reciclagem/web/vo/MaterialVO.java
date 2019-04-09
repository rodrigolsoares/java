package br.com.reciclagem.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MaterialVO  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int codigo;
	
	private String nome;
	
	private String descricao;

	private BigDecimal valorCompra;
	
	private BigDecimal valorVenda;

	private String UnidadeMedida;
	
	private BigDecimal quantidadeEmEstoque;
	
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
