package br.com.gestao.salao.dao;

import java.util.List;

import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface FuncionarioDao {
	
	List<AgendaVO> pesquisaHistoricoServicoFuncionario(FuncionarioVO func, UsuarioVO usuario, boolean somenteFechados) throws Exception;
	
	void inseriFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception;
	
	void atualizaFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception;
	
	boolean verificaFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception;
	
	List<FuncionarioVO> pesquisaFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception;
	
	List<FuncionarioVO> pesquisaFuncionarioAjudante(Integer codigo, UsuarioVO usuario) throws Exception;
	
	FuncionarioVO pesquisaFuncionarioPorCodigo(Integer codigo) throws Exception;
	
	List<FuncionarioVO> pesquisaFuncionario(SalaoVO salao) throws Exception;
}
