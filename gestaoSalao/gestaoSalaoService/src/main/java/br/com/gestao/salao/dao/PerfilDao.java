package br.com.gestao.salao.dao;

import java.util.List;

import br.com.gestao.salao.vo.PerfilVO;

public interface PerfilDao {
	
	List<PerfilVO> pesquisaPerfil() throws Exception;
	
}
