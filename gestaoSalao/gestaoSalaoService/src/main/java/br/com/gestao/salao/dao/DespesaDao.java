package br.com.gestao.salao.dao;

import java.util.List;

import br.com.gestao.salao.vo.DespesaVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface DespesaDao {
	
	void inseriDespesa(DespesaVO despesa, UsuarioVO usuario) throws Exception;
	
	void atualizaDespesa(DespesaVO despesa, UsuarioVO usuario) throws Exception;
	
	boolean verificaDespesa(DespesaVO despesa, UsuarioVO usuario) throws Exception;
	
	List<DespesaVO> pesquisaDespesa(DespesaVO despesa, UsuarioVO usuario) throws Exception;
	
	DespesaVO pesquisaDespesaPorCodigo(String codigo, UsuarioVO usuario) throws Exception;
}
