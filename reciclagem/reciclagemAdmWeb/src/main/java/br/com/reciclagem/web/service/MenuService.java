package br.com.reciclagem.web.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.reciclagem.web.entidade.MenuEntidade;
import br.com.reciclagem.web.repository.MenuRepository;
import br.com.reciclagem.web.vo.MenuVO;


public class MenuService implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private MenuRepository menuRepository;
	
	public List<MenuVO> getMenu() throws Exception{
		
		List<MenuEntidade> listaMenuEntidade = this.getDadosMenu();			
		List<MenuVO> listaMenu = this.parseMenuEntidadeByMenuVo(listaMenuEntidade);
		return this.montaEstruturaMenu(listaMenu);
		
	}
	
	
	private List<MenuVO> montaEstruturaMenu(List<MenuVO> pLista){
		Map<Integer, MenuVO> mapMenu = new HashMap<Integer, MenuVO>();
		
		for(MenuVO menu : pLista){
			
			MenuVO menuPai =  menu;
			
			if(menu.getNivel() != 0){
				menuPai = mapMenu.get(menu.getNivel());
				menuPai.getMenuFilho().add(menu);
			}
			
			mapMenu.put(menuPai.getCodigo(),menuPai);
		}
		
		return new ArrayList<MenuVO>(mapMenu.values());
	}
	
	private List<MenuVO> parseMenuEntidadeByMenuVo(List<MenuEntidade> listaMenuEntidade){
		
		List<MenuVO> listaMenu = new ArrayList<MenuVO>();
		
		for(MenuEntidade menuEntidade : listaMenuEntidade){
			MenuVO menoVO = new MenuVO();
			menoVO.setCodigo(menuEntidade.getCodigo());
			menoVO.setIcone(menuEntidade.getIcone());
			menoVO.setId(menuEntidade.getIdObjeto());
			menoVO.setNivel(menuEntidade.getNivel());
			menoVO.setNome(menuEntidade.getNome());
			menoVO.setUrl(menuEntidade.getUrl());
			listaMenu.add(menoVO);
		}
		
		return listaMenu;
		
	}
	
	private List<MenuEntidade> getDadosMenu() throws Exception{
		return menuRepository.getFindAll();
	}
	
	

}
