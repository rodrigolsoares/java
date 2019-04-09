package br.com.reciclagem.web.vo;

import java.util.ArrayList;
import java.util.List;

public class MenuVO {
	
	private int codigo;
	private String nome;
	private String icone;
	private String id;
	private String url;
	private int nivel;
	private List<MenuVO> menuFilho = new ArrayList<MenuVO>();
	
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
	
	public String getIcone() {
		return icone;
	}
	
	public void setIcone(String icone) {
		this.icone = icone;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public List<MenuVO> getMenuFilho() {
		return menuFilho;
	}

	public void setMenuFilho(List<MenuVO> menuFilho) {
		this.menuFilho = menuFilho;
	}
	
	
	
}
