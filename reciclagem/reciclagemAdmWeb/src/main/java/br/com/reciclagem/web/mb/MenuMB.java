package br.com.reciclagem.web.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import br.com.reciclagem.web.service.MenuService;
import br.com.reciclagem.web.vo.MenuVO;

@Named
@SessionScoped
public class MenuMB implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private MenuService menuService;
	
	private MenuModel model;
	
	@PostConstruct
	public void init() throws Exception {
		
		model = new DefaultMenuModel();
		//UsuarioVO usuario = MemoriaUsuario.getUsuarioMemoria();
		
		for(MenuVO menuPai : menuService.getMenu() ){
			
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
