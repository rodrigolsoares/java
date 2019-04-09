package br.com.reciclagem.web.vo;

public class UsuarioVO {

	private Integer codigo;
	private String nome;
	private String login;
	private String senha;
	private String confirmaSenha;
	private String mensagem;
	private PerfilVO perfil = new PerfilVO();
	
	
	public UsuarioVO(){
		
	}
	
	public UsuarioVO(String nomeUsuario, String login, int codigoPerfil){
		this.setNome(nomeUsuario);
		this.setLogin(login);
		this.setSenha("000");
		this.setConfirmaSenha("000");
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public PerfilVO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}

	
	
	
}
