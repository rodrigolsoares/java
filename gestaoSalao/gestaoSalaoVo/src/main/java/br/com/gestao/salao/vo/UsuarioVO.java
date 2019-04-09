package br.com.gestao.salao.vo;

public class UsuarioVO {

	private Integer codigo;
	private String nome;
	private String login;
	private String senha;
	private String confirmaSenha;
	private String mensagem;
	private ResponsavelVO responsavel = new ResponsavelVO();
	private SalaoVO salao = new SalaoVO();
	private PerfilVO perfil = new PerfilVO();
	private StatusRegistroVO status = new StatusRegistroVO();
	private boolean flagHabilitaAjudante;
	
	
	public UsuarioVO(){
		
	}
	
	public UsuarioVO(String nomeUsuario, String login, int codigoPerfil){
		this.setNome(nomeUsuario);
		this.setLogin(login);
		this.getPerfil().setCodigo(codigoPerfil);
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

	public ResponsavelVO getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(ResponsavelVO responsavel) {
		this.responsavel = responsavel;
	}

	public SalaoVO getSalao() {
		return salao;
	}

	public void setSalao(SalaoVO salao) {
		this.salao = salao;
	}

	public PerfilVO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}

	public StatusRegistroVO getStatus() {
		return status;
	}

	public void setStatus(StatusRegistroVO status) {
		this.status = status;
	}

	public boolean isFlagHabilitaAjudante() {
		return flagHabilitaAjudante;
	}

	public void setFlagHabilitaAjudante(boolean flagHabilitaAjudante) {
		this.flagHabilitaAjudante = flagHabilitaAjudante;
	}
	
	
}
