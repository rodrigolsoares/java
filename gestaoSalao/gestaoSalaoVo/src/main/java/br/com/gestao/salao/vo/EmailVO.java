package br.com.gestao.salao.vo;

public class EmailVO {
	
	private String distinatario;
	private String origem;
	private String nome;
	private String cabecalho;
	private String mensagem;
	private String motivo;
	
	public String getDistinatario() {
		return distinatario;
	}
	
	public void setDistinatario(String distinatario) {
		this.distinatario = distinatario;
	}
	
	public String getOrigem() {
		return origem;
	}
	
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	public String getCabecalho() {
		return cabecalho;
	}
	
	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
	
	
	
	
}
