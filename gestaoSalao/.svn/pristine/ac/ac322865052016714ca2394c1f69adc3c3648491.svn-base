package br.com.gestao.salao.service;

import java.util.List;
import java.util.Map;

import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface ServiceFuncionario {
	
	List<AgendaVO> pesquisaHistoricoServicoFuncionario(FuncionarioVO func, UsuarioVO usuario) throws Exception;
	
	String validaCamposObrigatorios(FuncionarioVO funcionario) throws Exception;
	
	List<FuncionarioVO> getListaFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception;
	
	boolean verificaSeServicoExiste(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception;
	
	void gravaFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception;
	
	void atualizarFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception;
	
	Map<String, Integer> getMapAjudante(Integer codigo, UsuarioVO usuario) throws Exception;
	
	FuncionarioVO pesquisaFuncionariobyCodigo(Integer codigo) throws Exception;
	
}
