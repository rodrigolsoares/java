package br.com.gestao.salao.dao;

import java.util.List;

import br.com.gestao.salao.vo.MenuVO;

public interface MenuDao {
	
	List<MenuVO> buscarPorCodigoUsuario(int idPerfil, int idPlano) throws Exception;
	List<MenuVO> buscarMobilePorCodigoUsuario(int idPerfil, int idPlano) throws Exception;
	
}
