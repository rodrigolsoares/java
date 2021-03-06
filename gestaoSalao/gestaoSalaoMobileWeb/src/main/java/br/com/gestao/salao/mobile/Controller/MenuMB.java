package br.com.gestao.salao.mobile.Controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.mobile.util.MemoriaUsuario;
import br.com.gestao.salao.service.ServiceMenu;
import br.com.gestao.salao.vo.MenuVO;
import br.com.gestao.salao.vo.UsuarioVO;

@SuppressWarnings("serial")
@Controller("menuMobileMB")
@Scope("view")
public class MenuMB implements Serializable{
	
	@Autowired
	private ServiceMenu serviceMenu;
	
	private List<MenuVO> listaMenu;
	
	@PostConstruct
	public void init() throws Exception {
	
		UsuarioVO usuario = MemoriaUsuario.getUsuarioMemoria();
		setListaMenu(serviceMenu.getMenuMobile(usuario) );
		
	}

	public List<MenuVO> getListaMenu() {
		return listaMenu;
	}

	public void setListaMenu(List<MenuVO> listaMenu) {
		this.listaMenu = listaMenu;
	}

	
	
	
	
}
