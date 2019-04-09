package br.com.gestao.salao.vo;

import br.com.gestao.salao.util.Formater;

public class ServicoVO {

	private Integer codigo;
	private String nome;
	private Double valor;
	private String descricao;
	private int status;
	private String sigla;
	private int flagOperacao;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFlagOperacao() {
		return flagOperacao;
	}

	public void setFlagOperacao(int flagOperacao) {
		this.flagOperacao = flagOperacao;
	}	
	
	@Override
	public String toString() {
		return nome + " R$ " + Formater.mascaraDinheiro(valor);
	}	
	
	
	
	
}
