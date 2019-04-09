package br.com.gestao.salao.dao;

import java.util.List;

import br.com.gestao.salao.vo.PlanoVO;

public interface PlanoDao {
	
	List<PlanoVO> pesquisarPlanos() throws Exception;
	
	List<PlanoVO> pesquisarPlanosByCodigo(Integer codigo) throws Exception;
}
