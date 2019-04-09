package br.com.gestao.salao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.dao.MenuDao;
import br.com.gestao.salao.service.ServiceMenu;
import br.com.gestao.salao.vo.MenuVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Service
@Transactional(readOnly=false)
public class ServiceMenuImpl implements ServiceMenu{
	
	@Autowired
	private MenuDao menuDao;
	
	public List<MenuVO> getMenu(UsuarioVO usuario) throws Exception{
		
		List<MenuVO> listaMenu = this.listBuscaMenuPorCodigoUsuario(usuario);				
		return this.montaEstruturaMenu(listaMenu);
		
	}
	
	public List<MenuVO> getMenuMobile(UsuarioVO usuario) throws Exception{
		
		List<MenuVO> listaMenu = this.listBuscaMenuPorCodigoUsuarioMobile(usuario);				
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
	
	private List<MenuVO> listBuscaMenuPorCodigoUsuario(UsuarioVO usuario) throws Exception{
		return menuDao.buscarPorCodigoUsuario(usuario.getPerfil().getCodigo(), usuario.getResponsavel().getPlano().getCodigo());
	}
	
	private List<MenuVO> listBuscaMenuPorCodigoUsuarioMobile(UsuarioVO usuario) throws Exception{
		return menuDao.buscarMobilePorCodigoUsuario(usuario.getPerfil().getCodigo(), usuario.getResponsavel().getPlano().getCodigo());
	}

}
