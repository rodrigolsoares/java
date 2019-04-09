package br.com.gestao.salao.service;

import java.util.List;

import br.com.gestao.salao.vo.DespesaVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface ServiceDespesa {
	
	String validaCamposObrigatorios(DespesaVO despesa);
	
	void gravar(DespesaVO despesa, UsuarioVO usuario) throws Exception;
	
	boolean verificaSeDespesaExiste(DespesaVO despesa, UsuarioVO usuario) throws Exception;
	
	void atualiza(DespesaVO despesa, UsuarioVO usuario) throws Exception;
	
	List<DespesaVO> pesquisar(DespesaVO despesa, UsuarioVO usuario) throws Exception; 
	
}
