package br.com.gestao.salao.vo;

public class ProdutoVO {

	private String codigo;
	private String nomeProduto;
	private String marca;
	private String descricao;
	private int qteEstoque;
	private int qteMinimaEstoque;
	private Double valorPago = new Double(0);
	private Double ValorTotal = new Double(0);
	private int status;
	private int verifica;
	private int flagOperacao;

	public int getVerifica() {
		return verifica;
	}

	public void setVerifica(int verifica) {
		this.verifica = verifica;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public int getQteEstoque() {
		return qteEstoque;
	}

	public void setQteEstoque(int qteEstoque) {
		this.qteEstoque = qteEstoque;
	}

	public int getQteMinimaEstoque() {
		return qteMinimaEstoque;
	}

	public void setQteMinimaEstoque(int qteMinimaEstoque) {
		this.qteMinimaEstoque = qteMinimaEstoque;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public Double getValorTotal() {
		return ValorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		ValorTotal = valorTotal;
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

	
}
