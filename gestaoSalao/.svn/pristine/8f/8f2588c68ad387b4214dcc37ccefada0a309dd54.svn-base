package br.com.gestao.salao.Controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.service.ServiceMenu;
import br.com.gestao.salao.util.MemoriaUsuario;
import br.com.gestao.salao.vo.MenuVO;
import br.com.gestao.salao.vo.UsuarioVO;

@SuppressWarnings("serial")
@Controller
@Scope("request")
public class MenuMB implements Serializable{
	
	@Autowired
	private ServiceMenu serviceMenu;
	
	private MenuModel model;
	
	@PostConstruct
	public void init() throws Exception {
		
		model = new DefaultMenuModel();
		UsuarioVO usuario = MemoriaUsuario.getUsuarioMemoria();
		
		for(MenuVO menuPai : serviceMenu.getMenu(usuario) ){
			
			Submenu submenu = new Submenu();
			submenu.setLabel(menuPai.getNome());
			submenu.setId(menuPai.getId());
			
			for(MenuVO menuFilho : menuPai.getMenuFilho()){
				
				MenuItem item = new MenuItem();
				item.setValue(menuFilho.getNome());
				item.setUrl(menuFilho.getUrl());
				item.setIcon(menuFilho.getIcone());
				item.setId(menuFilho.getId());
				item.setAjax(true);
				item.setUpdate("frm");
				submenu.getChildren().add(item);
			}
			
			model.addSubmenu(submenu);
				
		}
		
		
	}
	
	public MenuModel getModel() { return model; }
	
}
