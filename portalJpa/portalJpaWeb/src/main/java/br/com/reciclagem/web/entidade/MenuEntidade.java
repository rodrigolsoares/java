package br.com.reciclagem.web.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class MenuEntidade  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "pk_menu", nullable = true)
	private int codigo;
	
	@Column(name = "nm_menu", nullable = true)
	private String nome;
	
	@Column(name = "nm_icone", nullable = true)
	private String icone;
	
	@Column(name = "id_obj", nullable = true)
	private String idObjeto;
	
	@Column(name = "nm_url", nullable = true)
	private String url;
	
	@Column(name = "nivel", nullable = true)
	private int nivel;
	
	
	public int getCodigo() {
		return codigo;
	}
	
	
	public String getNome() {
		return nome;
	}
	
	
	public String getIcone() {
		return icone;
	}
	
	
	public String getIdObjeto() {
		return idObjeto;
	}
	
	
	public String getUrl() {
		return url;
	}
	
	
	public int getNivel() {
		return nivel;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setIcone(String icone) {
		this.icone = icone;
	}
	
	public void setIdObjeto(String idObjeto) {
		this.idObjeto = idObjeto;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	

}
